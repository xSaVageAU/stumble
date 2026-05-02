package savage.stumble;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stumble implements ModInitializer {
	public static final String MOD_ID = "stumble";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final AttachmentType<StumbleState> STUMBLE_STATE = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(MOD_ID, "state"),
			builder -> builder.persistent(StumbleState.CODEC).initializer(() -> StumbleState.NONE).copyOnDeath()
	);

	public static final AttachmentType<Integer> STUMBLE_TICKS = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(MOD_ID, "ticks"),
			builder -> builder.persistent(com.mojang.serialization.Codec.INT).initializer(() -> 0).copyOnDeath()
	);

	@Override
	public void onInitialize() {
		StumbleHandler.init();

		LOGGER.info("Stumble mod initialized!");
	}
}