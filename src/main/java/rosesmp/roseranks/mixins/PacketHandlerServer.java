package rosesmp.roseranks.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.net.ChatEmotes;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.net.packet.PacketDisconnect;
import net.minecraft.core.net.packet.PacketMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.entity.player.PlayerServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rosesmp.roseranks.RoseRanks;
import rosesmp.roseranks.data.User;
import java.io.IOException;

import static rosesmp.roseranks.RoseRanks.getUsers;

@Mixin(value = net.minecraft.server.net.handler.PacketHandlerServer.class, remap = false)
public class PacketHandlerServer {

	@Shadow private PlayerServer playerEntity;
	@Shadow @Final private MinecraftServer mcServer;

	/**
	 * Handles unloading a user's data upon disconnect.
	 */
	@Inject(at = @At(value = "HEAD"), method = "handleDisconnect", remap = false)
	public void handleDisconnect(PacketDisconnect packetDisconnect, CallbackInfo ci) throws IOException {
		User user = getUsers().get(playerEntity.username);
		user.save();

		getUsers().remove(playerEntity.username);
	}

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/core/net/ChatEmotes;process(Ljava/lang/String;)Ljava/lang/String;"), method = "handleMessage", remap = false, cancellable = true)
	public void handleMessage(PacketMessage packet, CallbackInfo ci, @Local(name = "message") String message) throws IOException {
		User user = getUsers().get(playerEntity.username);
		String prefix = user.getPrefix();
		prefix = ChatEmotes.process(prefix);

		//Apply configured chat formatting and add player's prefix
		message = prefix + playerEntity.getDisplayName() + "§0: " + TextFormatting.RESET + message;

		RoseRanks.LOGGER.info(message);
		this.mcServer.playerList.sendEncryptedChatToAllPlayers(message);
		ci.cancel();
	}
}
