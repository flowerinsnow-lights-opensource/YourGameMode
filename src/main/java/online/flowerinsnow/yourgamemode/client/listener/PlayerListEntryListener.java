package online.flowerinsnow.yourgamemode.client.listener;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.GameMode;
import online.flowerinsnow.yourgamemode.client.eci.PlayerListEntryGameModeChangeEvent;

public class PlayerListEntryListener implements PlayerListEntryGameModeChangeEvent {
    @Override
    public ActionResult onGameModeChange(PlayerListEntry entry, GameMode newGameMode) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            String gameMode;
            switch (newGameMode) {
                case CREATIVE -> gameMode = I18n.translate("gameMode.creative");
                case SURVIVAL -> gameMode = I18n.translate("gameMode.survival");
                case ADVENTURE -> gameMode = I18n.translate("gameMode.adventure");
                case SPECTATOR -> gameMode = I18n.translate("gameMode.spectator");
                default -> gameMode = "UNKNOWN";
            }
            mc.player.sendMessage(Text.translatable("yourgamemode.change", entry.getProfile().getName(), gameMode));
        }
        return ActionResult.PASS;
    }
}
