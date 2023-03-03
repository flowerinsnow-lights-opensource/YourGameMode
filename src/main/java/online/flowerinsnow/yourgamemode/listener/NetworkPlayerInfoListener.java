package online.flowerinsnow.yourgamemode.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.yourgamemode.event.NetworkPlayerInfoEvent;
import online.flowerinsnow.yourgamemode.util.MessageUtils;

import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;

@SideOnly(Side.CLIENT)
public class NetworkPlayerInfoListener {
    private final HashMap<UUID, WorldSettings.GameType> lastKnown = new HashMap<>();
    private WorldClient lastWorld;
    private static final Pattern MINECRAFT_USERNAME = Pattern.compile("[a-zA-Z0-9_]{3,16}");

    @SubscribeEvent
    public void onAdd(NetworkPlayerInfoEvent.Add event) {
        WorldSettings.GameType last = lastKnown.get(event.profile.getId());
        if (last != null && last == event.gameMode) {
            return;
        }
        lastKnown.put(event.profile.getId(), event.gameMode);

        if (!MINECRAFT_USERNAME.matcher(event.profile.getName()).matches()) {
            return;
        }

        MessageUtils.showText(
                "yourgamemode.add",
                event.profile.getName(),
                MessageUtils.getGameModeName(event.gameMode)
        );
    }

    @SubscribeEvent
    public void onGameMode(NetworkPlayerInfoEvent.GameMode event) {
        WorldSettings.GameType last = lastKnown.get(event.profile.getId());
        if (last != null && last == event.newGameMode) {
            return;
        }
        lastKnown.put(event.profile.getId(), event.newGameMode);

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
