package online.flowerinsnow.yourgamemode.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import online.flowerinsnow.yourgamemode.event.NetworkPlayerInfoEvent;
import online.flowerinsnow.yourgamemode.util.MessageUtils;

import java.util.HashMap;
import java.util.UUID;

public class NetworkPlayerInfoListener {
    private final HashMap<UUID, WorldSettings.GameType> lastKnown = new HashMap<>();
    private WorldClient lastWorld;

    @SubscribeEvent
    public void onAdd(NetworkPlayerInfoEvent.Add event) {
        WorldSettings.GameType last = lastKnown.get(event.profile.getId());
        if (last == event.gameMode) {
            return;
        }
        lastKnown.put(event.profile.getId(), last);

        MessageUtils.showText(
                "yourgamemode.add",
                event.profile.getName(),
                MessageUtils.getGameModeName(event.gameMode)
        );
    }

    @SubscribeEvent
    public void onGameMode(NetworkPlayerInfoEvent.GameMode event) {
        WorldSettings.GameType last = lastKnown.get(event.profile.getId());
        if (last == event.gameMode) {
            return;
        }
        lastKnown.put(event.profile.getId(), last);

        MessageUtils.showText("yourgamemode.change", event.profile.getName(), MessageUtils.getGameModeName(event.newGameMode));
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        WorldClient world = Minecraft.getMinecraft().theWorld;
        if (world != lastWorld) {
            lastWorld = world;
            lastKnown.clear();
        }
    }
}
