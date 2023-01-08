package online.flowerinsnow.yourgamemode.client.eci;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.ActionResult;
import net.minecraft.world.GameMode;

public interface PlayerListEntryGameModeChangeEvent {
    Event<PlayerListEntryGameModeChangeEvent> EVENT = EventFactory.createArrayBacked(PlayerListEntryGameModeChangeEvent.class,
            listeners -> (entry, newGameMode) -> {
                for (PlayerListEntryGameModeChangeEvent listener : listeners) {
                    ActionResult result = listener.onGameModeChange(entry, newGameMode);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });
    ActionResult onGameModeChange(PlayerListEntry entry, GameMode newGameMode);
}
