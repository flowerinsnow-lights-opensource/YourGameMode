package online.flowerinsnow.yourgamemode.listener;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerChangeGameModeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.flowerinsnow.yourgamemode.event.NewNetworkPlayerInfoEvent;

import java.util.HashMap;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class ClientListener {
    private final HashMap<UUID, GameType> lastKnown = new HashMap<>();

    private ClientWorld lastWorld;

    @SubscribeEvent
    public void onNewNetworkPlayerInfo(NewNetworkPlayerInfoEvent event) {
        GameProfile profile = event.gameProfile;
        GameType gameType = lastKnown.get(profile.getId());
        if (gameType != event.gameType) {
            ClientPlayerEntity self = Minecraft.getInstance().player;
            if (self != null) {
                //noinspection DataFlowIssue
                self.sendMessage(new TranslationTextComponent("yourgamemode.add", profile.getName(), I18n.format(getTranslationKeyByGameMode(event.gameType))), null);
            }
            lastKnown.put(profile.getId(), event.gameType);
        }
    }

    @SubscribeEvent
    public void onClientPlayerChangeGameMode(ClientPlayerChangeGameModeEvent event) {
        GameProfile profile = event.getInfo().getGameProfile();
        GameType gameType = lastKnown.get(profile.getId());
        if (gameType != event.getNewGameMode()) {
            ClientPlayerEntity self = Minecraft.getInstance().player;
            if (self != null) {
                //noinspection DataFlowIssue
                self.sendMessage(new TranslationTextComponent("yourgamemode.change", event.getInfo().getGameProfile().getName(), I18n.format(getTranslationKeyByGameMode(event.getNewGameMode()))), null);
            }
            lastKnown.put(profile.getId(), event.getNewGameMode());
        }
    }

    @SubscribeEvent
    public void onWorldChange(TickEvent.ClientTickEvent event) {
        ClientWorld nowWorld = Minecraft.getInstance().world;
        if (nowWorld != lastWorld) {
            lastKnown.clear();
            lastWorld = nowWorld;
        }
    }

    private static String getTranslationKeyByGameMode(GameType gameType) {
        switch (gameType) {
            case SURVIVAL:
                return "gameMode.survival";
            case CREATIVE:
                return "gameMode.creative";
            case ADVENTURE:
                return "gameMode.adventure";
            case SPECTATOR:
                return "gameMode.spectator";
            default:
                throw new RuntimeException();
        }
    }
}
