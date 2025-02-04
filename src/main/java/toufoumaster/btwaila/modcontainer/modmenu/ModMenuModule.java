package toufoumaster.btwaila.modcontainer.modmenu;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.GuiScreen;
import toufoumaster.btwaila.BTWaila;
import toufoumaster.btwaila.gui.GuiBTWailaOption;

import java.util.function.Function;

public class ModMenuModule implements ModMenuApi {
    @Override
    public String getModId() {
        return BTWaila.MOD_ID;
    }

    @Override
    public Function<GuiScreen, ? extends GuiScreen> getConfigScreenFactory() {
        return (screenBase -> new GuiBTWailaOption());
    }
}
