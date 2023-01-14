package online.flowerinsnow.yourgamemode.listener;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import online.flowerinsnow.yourgamemode.event.NetworkPlayerInfoEvent;
import online.flowerinsnow.yourgamemode.util.MessageUtils;

public class NetworkPlayerInfoListener {
    @SubscribeEvent
    public void onAdd(NetworkPlayerInfoEvent.Add event) {
        MessageUtils.showText(
                "yourgamemode.add",
                event.profile.getName(),
                MessageUtils.getGameModeName(event.gameMode)
        );
    }

    @SubscribeEvent
    public void onGameMode(NetworkPlayerInfoEvent.GameMode event) {
        MessageUtils.showText("yourgamemode.change", event.profile.getName(), MessageUtils.getGameModeName(event.newGameMode));
    }
}
