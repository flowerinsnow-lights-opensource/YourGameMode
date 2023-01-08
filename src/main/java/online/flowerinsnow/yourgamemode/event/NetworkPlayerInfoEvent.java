package online.flowerinsnow.yourgamemode.event;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.common.eventhandler.Event;

public class NetworkPlayerInfoEvent extends Event {
    public final GameProfile profile;
    public final WorldSettings.GameType gameMode;

    protected NetworkPlayerInfoEvent(GameProfile profile, WorldSettings.GameType gameMode) {
        this.profile = profile;
        this.gameMode = gameMode;
    }

    public static class Add extends NetworkPlayerInfoEvent {
        public Add(GameProfile profile, WorldSettings.GameType gameMode) {
            super(profile, gameMode);
        }
    }

    public static class GameMode extends NetworkPlayerInfoEvent {
        public final WorldSettings.GameType newGameMode;
        public GameMode(GameProfile profile, WorldSettings.GameType gameMode, WorldSettings.GameType newGameMode) {
            super(profile, gameMode);
            this.newGameMode = newGameMode;
        }
    }
}
