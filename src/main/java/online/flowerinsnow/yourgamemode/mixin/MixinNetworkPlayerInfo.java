package online.flowerinsnow.yourgamemode.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.world.GameType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.yourgamemode.event.NetworkPlayerInfoEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkPlayerInfo.class)
@SideOnly(Side.CLIENT)
public class MixinNetworkPlayerInfo {
    @Shadow
    @Final
    private GameProfile gameProfile;
    @Shadow
    private GameType gameType;

    @Inject(
            method = "<init>(Lnet/minecraft/network/play/server/SPacketPlayerListItem$AddPlayerData;)V",
            at = @At("RETURN")
    )
    public void init(SPacketPlayerListItem.AddPlayerData entry, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new NetworkPlayerInfoEvent.Add(gameProfile, gameType));
    }

    @Inject(
            method = "setGameType",
            at = @At("HEAD")
    )
    public void setGameType(GameType gameMode, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new NetworkPlayerInfoEvent.GameMode(gameProfile, gameType, gameMode));
    }
}
