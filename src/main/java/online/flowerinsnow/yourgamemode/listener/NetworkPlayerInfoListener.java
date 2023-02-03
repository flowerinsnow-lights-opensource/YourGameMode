package online.flowerinsnow.yourgamemode.listener;

import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import online.flowerinsnow.yourgamemode.event.ClientWorldChangeEvent;
import online.flowerinsnow.yourgamemode.event.NetworkPlayerInfoEvent;
import online.flowerinsnow.yourgamemode.util.MessageUtils;

import java.util.HashMap;
import java.util.UUID;

public class NetworkPlayerInfoListener {
    private final HashMap<UUID, GameType> lastKnown = new HashMap<>();

    @SubscribeEvent
    public void onAdd(NetworkPlayerInfoEvent.Add event) {
        GameType type = lastKnown.get(event.profile.getId());
        if (type == event.gameMode) {
            return;
        }
        lastKnown.put(event.profile.getId(), event.gameMode);
        MessageUtils.showText(
                "yourgamemode.add",
                event.profile.getName(),
                MessageUtils.getGameModeName(event.gameMode)
        );
    }

    @SubscribeEvent
    public void onGameMode(NetworkPlayerInfoEvent.GameMode event) {
        GameType type = lastKnown.get(event.profile.getId());
        if (type == event.gameMode) {
            return;
        }
        lastKnown.put(event.profile.getId(), event.gameMode);
        MessageUtils.showText("yourgamemode.change", event.profile.getName(), MessageUtils.getGameModeName(event.newGameMode));
    }

    @SubscribeEvent
    public void onWorldChange(ClientWorldChangeEvent e) {
        lastKnown.clear();
    }
}
