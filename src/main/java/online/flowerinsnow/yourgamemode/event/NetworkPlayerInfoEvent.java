package online.flowerinsnow.yourgamemode.event;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NetworkPlayerInfoEvent extends Event {
    public final GameProfile profile;
    public final GameType gameMode;

    protected NetworkPlayerInfoEvent(GameProfile profile, GameType gameMode) {
        this.profile = profile;
        this.gameMode = gameMode;
    }

    public static class Add extends NetworkPlayerInfoEvent {
        public Add(GameProfile profile, GameType gameMode) {
            super(profile, gameMode);
        }
    }

    public static class GameMode extends NetworkPlayerInfoEvent {
        public final GameType newGameMode;
        public GameMode(GameProfile profile, GameType gameMode, GameType newGameMode) {
            super(profile, gameMode);
            this.newGameMode = newGameMode;
        }
    }
}
