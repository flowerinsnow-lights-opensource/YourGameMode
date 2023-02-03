package online.flowerinsnow.yourgamemode.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.world.WorldSettings;
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
    private WorldSettings.GameType gameType;

    @Inject(
            method = "<init>(Lnet/minecraft/network/play/server/S38PacketPlayerListItem$AddPlayerData;)V",
            at = @At("RETURN")
    )
    private void init(S38PacketPlayerListItem.AddPlayerData p_i46295_1_, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new NetworkPlayerInfoEvent.Add(p_i46295_1_.getProfile(), p_i46295_1_.getGameMode()));
    }

    @Inject(
            method = "setGameType",
            at = @At("HEAD")
    )
    private void setGameType(WorldSettings.GameType p_178839_1_, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new NetworkPlayerInfoEvent.GameMode(gameProfile, gameType, p_178839_1_));
    }
}
