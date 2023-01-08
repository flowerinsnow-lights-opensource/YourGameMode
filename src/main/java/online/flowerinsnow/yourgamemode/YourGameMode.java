package online.flowerinsnow.yourgamemode;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.yourgamemode.listener.NetworkPlayerInfoListener;

@Mod(
        modid = YourGameMode.MODID,
        version = YourGameMode.VERSION,
        clientSideOnly = true
)
@SideOnly(Side.CLIENT)
public class YourGameMode {
    public static final String MODID = "yourgamemode";
    public static final String VERSION = "@VERSION@";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new NetworkPlayerInfoListener());
    }
}
