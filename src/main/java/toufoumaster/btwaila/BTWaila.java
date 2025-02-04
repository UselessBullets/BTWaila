package toufoumaster.btwaila;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HitResult;
import net.minecraft.core.block.entity.*;
import net.minecraft.core.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import toufoumaster.btwaila.gui.GuiBlockOverlay;
import toufoumaster.btwaila.mixin.PacketMixin;
import toufoumaster.btwaila.network.packet.PacketEntityData;
import toufoumaster.btwaila.network.packet.PacketRequestEntityData;
import toufoumaster.btwaila.network.packet.PacketRequestTileEntityData;
import toufoumaster.btwaila.util.VersionHelper;

import java.util.HashMap;
import java.util.Map;


public class BTWaila implements ModInitializer {
    public static final String MOD_ID = "btwaila";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final GuiBlockOverlay blockOverlay = new GuiBlockOverlay();
    public static boolean isHalpPresent = FabricLoader.getInstance().isModLoaded("halplibe");
    public static boolean showBlockOverlay = false;
    public static boolean showEntityOverlay = false;
    public static HitResult blockToDraw = null;
    public static int blockMetadata = 0;
    public static Entity entityToDraw;
    public static boolean canUseAdvancedTooltips = false;
    public static VersionHelper modVersion = new VersionHelper(0, 2, 2);
    public static String checkString = modVersion.generateCheckString();
    public static Map<Class<TileEntity>, Boolean> excludeContinousTileEntityData = new HashMap<Class<TileEntity>, Boolean>();
    public static void excludeContinuousTileEntityPacketUpdateClass(Class tileEntityClass) {
        excludeContinousTileEntityData.put(tileEntityClass, true);
    }
    static {
        excludeContinuousTileEntityPacketUpdateClass(TileEntitySign.class);
        excludeContinuousTileEntityPacketUpdateClass(TileEntityFlag.class);
    }

    public BTWaila() {
        PacketMixin.callAddIdClassMapping(220, false, true, PacketRequestTileEntityData.class);
        PacketMixin.callAddIdClassMapping(221, false, true, PacketRequestEntityData.class);
        PacketMixin.callAddIdClassMapping(222, true, false, PacketEntityData.class);
        Object instance = FabricLoader.getInstance().getGameInstance();
        if (instance instanceof Minecraft) {
            blockOverlay.setMinecraftInstance((Minecraft) instance);
        }
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Loading implementations.");

        FabricLoader.getInstance().getEntrypointContainers("btwaila", BTWailaCustomTootltipPlugin.class).forEach(plugin -> {
            plugin.getEntrypoint().initializePlugin(LOGGER);
        });

        LOGGER.info("BTWaila initialized.");
        System.out.println(modVersion);
    }
}
