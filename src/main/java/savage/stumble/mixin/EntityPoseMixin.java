package savage.stumble.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import savage.stumble.Stumble;
import savage.stumble.StumbleState;

@Mixin(Entity.class)
public abstract class EntityPoseMixin {
	@Inject(method = "getPose", at = @At("HEAD"), cancellable = true)
	private void stumble$getPose(CallbackInfoReturnable<Pose> cir) {
		Object self = (Object) this;
		if (self instanceof ServerPlayer player) {
			StumbleState state = player.getAttachedOrCreate(Stumble.STUMBLE_STATE);
			if (state == StumbleState.CRAWLING) {
				cir.setReturnValue(Pose.SWIMMING);
			}
		}
	}
}
