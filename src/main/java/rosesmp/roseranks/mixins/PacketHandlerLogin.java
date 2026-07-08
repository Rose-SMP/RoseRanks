package rosesmp.roseranks.mixins;

import net.minecraft.core.net.packet.PacketLogin;
import net.minecraft.core.util.helper.UUIDHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rosesmp.roseranks.data.User;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static rosesmp.roseranks.RoseRanks.getUsers;
import static rosesmp.roseranks.RoseRanks.yamlService;

@Mixin(value = net.minecraft.server.net.handler.PacketHandlerLogin.class, remap = false)
public class PacketHandlerLogin {

	@Shadow private String username;

	/**
	 * Handles loading a user's data upon login.
	 */
	@Inject(at = @At(value = "TAIL"), method = "doLogin", remap = false)
	public void doLogin(PacketLogin loginPacket, CallbackInfo ci) throws IOException {
		File file = yamlService.usersFile;
		UUID uuid = Objects.requireNonNull(UUIDHelper.getUUIDFromName(username));

		getUsers().put(username, new User(uuid));
	}
}
