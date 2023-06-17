package online.flowerinsnow.yourgamemode.mixin;

import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.network.play.server.SPlayerListItemPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import online.flowerinsnow.yourgamemode.event.NewNetworkPlayerInfoEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkPlayerInfo.class)
@OnlyIn(Dist.CLIENT)
public class MixinNetworkPlayerInfo {
    @Shadow
    private GameType gameType;

    @Shadow
    private int responseTime;

    @Shadow
    private ITextComponent displayName;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    public void init(SPlayerListItemPacket.AddPlayerData entry, CallbackInfo ci) {
        NewNetworkPlayerInfoEvent event = new NewNetworkPlayerInfoEvent(entry.getProfile(), entry.getGameMode(), entry.getPing(), entry.getDisplayName());
        MinecraftForge.EVENT_BUS.post(event);

        this.gameType = event.gameType;
        this.responseTime = event.responseTime;
        this.displayName = event.displayName;
    }
}
