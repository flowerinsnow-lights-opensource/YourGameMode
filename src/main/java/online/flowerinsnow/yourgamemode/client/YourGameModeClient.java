package online.flowerinsnow.yourgamemode.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import online.flowerinsnow.yourgamemode.client.eci.PlayerListEntryGameModeChangeEvent;
import online.flowerinsnow.yourgamemode.client.listener.PlayerListEntryListener;

@Environment(EnvType.CLIENT)
public class YourGameModeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlayerListEntryGameModeChangeEvent.EVENT.register(new PlayerListEntryListener());
    }
}
