package online.flowerinsnow.yourgamemode.event;

import com.mojang.authlib.GameProfile;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameType;
import net.minecraftforge.eventbus.api.Event;

public class NewNetworkPlayerInfoEvent extends Event {
    public final GameProfile gameProfile;
    public GameType gameType;
    public int responseTime;
    public ITextComponent displayName;

    public NewNetworkPlayerInfoEvent(GameProfile gameProfile, GameType gameType, int responseTime, ITextComponent displayName) {
        this.gameProfile = gameProfile;
        this.gameType = gameType;
        this.responseTime = responseTime;
        this.displayName = displayName;
    }
}
