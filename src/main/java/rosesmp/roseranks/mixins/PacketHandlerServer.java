package rosesmp.roseranks.mixins;

import net.minecraft.core.net.packet.PacketDisconnect;
import net.minecraft.server.entity.player.PlayerServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rosesmp.roseranks.data.User;
import java.io.IOException;

import static rosesmp.roseranks.RoseRanks.getUsers;

@Mixin(value = net.minecraft.server.net.handler.PacketHandlerServer.class, remap = false)
public class PacketHandlerServer {

	@Shadow private PlayerServer playerEntity;

	/**
	 * Handles unloading a user's data upon disconnect.
	 */
	@Inject(at = @At(value = "HEAD"), method = "handleDisconnect", remap = false)
	public void handleDisconnect(PacketDisconnect packetDisconnect, CallbackInfo ci) throws IOException {
		User user = getUsers().get(playerEntity.username);
		user.save();

		getUsers().remove(playerEntity.username);
	}
}
