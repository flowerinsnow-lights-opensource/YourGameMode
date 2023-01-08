package online.flowerinsnow.yourgamemode.client.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.world.GameMode;
import online.flowerinsnow.yourgamemode.client.eci.PlayerListEntryGameModeChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerListEntry.class)
@Environment(EnvType.CLIENT)
public class MixinPlayerListEntry {
    private final PlayerListEntry THIS = (PlayerListEntry) (Object) this;
    @Inject(
            method = "setGameMode",
            at = @At("HEAD")
    )
    private void setGameMode(GameMode gameMode, CallbackInfo ci) {
        PlayerListEntryGameModeChangeEvent.EVENT.invoker().onGameModeChange(THIS, gameMode);
    }
}
