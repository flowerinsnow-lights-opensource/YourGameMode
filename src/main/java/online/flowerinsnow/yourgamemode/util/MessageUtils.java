package online.flowerinsnow.yourgamemode.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.GameType;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MessageUtils {
    public static void showText(String key, Object... args) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) {
            return;
        }
        TextComponentTranslation text = new TextComponentTranslation(key, args);
        ClientChatReceivedEvent event = new ClientChatReceivedEvent(ChatType.CHAT, text);
        if (!MinecraftForge.EVENT_BUS.post(event)) {
            mc.player.sendMessage(event.getMessage());
        }
    }

    public static TextComponentTranslation getGameModeName(GameType gameType) {
        return new TextComponentTranslation("gameMode." + gameType.getName());
    }
}
