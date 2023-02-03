package online.flowerinsnow.yourgamemode.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import online.flowerinsnow.yourgamemode.event.ClientWorldChangeEvent;

public class EventTrigger {
    private WorldClient lastWorld;

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient world = mc.world;
        if (lastWorld != world) {
            MinecraftForge.EVENT_BUS.post(new ClientWorldChangeEvent(lastWorld, world));
            lastWorld = world;
        }
    }
}
