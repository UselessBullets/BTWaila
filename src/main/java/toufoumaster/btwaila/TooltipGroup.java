package toufoumaster.btwaila;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityBlastFurnace;
import net.minecraft.core.block.entity.TileEntityChest;

import java.util.ArrayList;
import java.util.List;

public class TooltipGroup {

    private final String modId;
    private final Class interfaceClass;
    private final IBTWailaCustomTooltip customTooltip;
    private List<Class<TileEntity>> tileEntityList = new ArrayList<>();
    public TooltipGroup(String modId, Class interfaceClass, IBTWailaCustomTooltip customTooltip) {
        this.modId = modId;
        this.interfaceClass = interfaceClass;
        this.customTooltip = customTooltip;
    }

    public void addTooltip(Class tileEntityClass) {
        tileEntityList.add(tileEntityClass);
    }

    public Class getInterfaceClass() {
        return interfaceClass;
    }

    public IBTWailaCustomTooltip getCustomTooltip() {
        return customTooltip;
    }

    public boolean isInList(Class entityClass) {
        return tileEntityList.contains(entityClass);
    }
}
