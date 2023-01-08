package online.flowerinsnow.yourgamemode.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MessageUtils {
    public static void showText(String key, Object... args) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) {
            return;
        }
        String message = I18n.format(key, args);
        ChatComponentText text = new ChatComponentText(message);
        ClientChatReceivedEvent event = new ClientChatReceivedEvent((byte) 0, text);
        if (!MinecraftForge.EVENT_BUS.post(event)) {
            mc.thePlayer.addChatMessage(event.message);
        }
    }

    public static String getGameModeName(WorldSettings.GameType gameType) {
        switch (gameType) {
            case SURVIVAL:
                return I18n.format("gameMode.survival");
            case CREATIVE:
                return I18n.format("gameMode.creative");
            case ADVENTURE:
                return I18n.format("gameMode.adventure");
            case SPECTATOR:
                return I18n.format("gameMode.spectator");
            default:
                return "UNKNOWN";
        }
    }
}
