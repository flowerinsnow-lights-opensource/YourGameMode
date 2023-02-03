package online.flowerinsnow.yourgamemode.event;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ClientWorldChangeEvent extends Event {
    public final WorldClient oldWorld;
    public final WorldClient newWorld;

    public ClientWorldChangeEvent(WorldClient oldWorld, WorldClient newWorld) {
        this.oldWorld = oldWorld;
        this.newWorld = newWorld;
    }
}
