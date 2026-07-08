package rosesmp.roseranks.mixins;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = net.minecraft.server.net.handler.PacketHandlerServer.class, remap = false)
public class PacketHandlerServer {
}
