package savage.stumble;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Pose;

public class StumbleHandler {
	public static void init() {
		ServerTickEvents.START_SERVER_TICK.register(server -> {
			for (ServerPlayer player : server.getPlayerList().getPlayers()) {
				StumbleState state = player.getAttachedOrCreate(Stumble.STUMBLE_STATE);
				int ticks = player.getAttachedOrCreate(Stumble.STUMBLE_TICKS);

				if (ticks > 0) {
					player.setAttached(Stumble.STUMBLE_TICKS, ticks - 1);
					if (ticks - 1 == 0) {
						player.setAttached(Stumble.STUMBLE_STATE, StumbleState.NONE);
					}
				}

				if (state == StumbleState.CRAWLING) {
					player.setPose(Pose.SWIMMING);
					player.setSwimming(true);
				}
			}
		});
	}
}
