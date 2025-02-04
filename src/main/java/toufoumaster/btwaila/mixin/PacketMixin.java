package toufoumaster.btwaila.mixin;

import net.minecraft.core.net.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = Packet.class, remap = false)
public
interface PacketMixin {

    @Invoker("addIdClassMapping")
    static void callAddIdClassMapping(int i, boolean clientPacket, boolean serverPacket, Class class1) {
        throw new UnsupportedOperationException();
    }
}
