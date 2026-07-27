package rosesmp.roseranks.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.net.ChatEmotes;
import net.minecraft.core.net.packet.PacketDisconnect;
import net.minecraft.core.net.packet.PacketMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.entity.player.PlayerServer;
import net.minecraft.server.net.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rosesmp.roseranks.RoseRanks;
import rosesmp.roseranks.data.User;
import java.io.IOException;

import static rosesmp.roseranks.RoseRanks.config;
import static rosesmp.roseranks.RoseRanks.getLoadedUsers;

@Mixin(value = net.minecraft.server.net.handler.PacketHandlerServer.class, remap = false)
public class PacketHandlerServer {

	@Shadow private PlayerServer playerEntity;
	@Shadow @Final private MinecraftServer mcServer;

	/**
	 * Handles unloading a user's data upon disconnect.
	 */
	@Inject(at = @At(value = "HEAD"), method = "handleDisconnect", remap = false)
	public void onDisconnect(PacketDisconnect packetDisconnect, CallbackInfo ci) throws IOException {
		User user = getLoadedUsers().get(playerEntity.username);
		user.save();

		getLoadedUsers().remove(playerEntity.username);
	}

	/**
	 * Adds prefixes and applies configured chat formatting to player chat messages.
	 */
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/core/net/ChatEmotes;process(Ljava/lang/String;)Ljava/lang/String;"), method = "handleMessage", remap = false, cancellable = true)
	public void onChat(PacketMessage packet, CallbackInfo ci, @Local(name = "message") String message) throws IOException {
		//Get player's prefix
		User user = getLoadedUsers().get(playerEntity.username);
		String prefix = user.getPrefix();
		prefix = ChatEmotes.process(prefix);

		//Apply configured chat formatting and add player's prefix
		String format = config().getYaml().getString("chatFormat");
		message = format
			.replace("{prefix}", prefix)
			.replace("{name}", playerEntity.getDisplayName())
			.replace("{message}", message);

		RoseRanks.LOGGER.info(message);
		mcServer.playerList.sendEncryptedChatToAllPlayers(message);
		ci.cancel();
	}

	/**
	 * Implements custom permissions system
	 */
	@Inject(at = @At(value = "HEAD"), method = "handleSlashCommand", remap = false)
	public void onCommand(String s, CallbackInfo ci) throws IOException {
		ServerCommandSource serverCommandSource = new ServerCommandSource(this.mcServer, this.playerEntity);

		//Remove slash, then split each word, then get just the first
		String commandLabel = s.substring(1).split(" ")[0];

		//Deny command execution if the sender lacks the necessary permission, unless they're an operator
		User user = getLoadedUsers().get(playerEntity.username);
		if (!user.hasPermission("minecraft." + commandLabel) && !playerEntity.isOperator()) {
			playerEntity.sendMessage("§eYou do not have permission to execute /" + commandLabel + "!");
		}
		//TODO: Empower permissions to grant access to op commands to non-ops
	}
}
