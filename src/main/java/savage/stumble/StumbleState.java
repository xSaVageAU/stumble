package savage.stumble;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum StumbleState implements StringRepresentable {
	NONE("none"),
	CRAWLING("crawling"),
	SITTING("sitting");

	public static final Codec<StumbleState> CODEC = StringRepresentable.fromEnum(StumbleState::values);
	private final String name;

	StumbleState(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}
}
