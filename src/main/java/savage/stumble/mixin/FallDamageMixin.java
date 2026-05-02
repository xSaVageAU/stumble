package savage.stumble.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import savage.stumble.Stumble;

@Mixin(LivingEntity.class)
public abstract class FallDamageMixin {
	@Inject(method = "causeFallDamage", at = @At("HEAD"))
	private void stumble$onFallDamage(double fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
		Object self = (Object) this;
		if (self instanceof ServerPlayer player) {
			// Basic damage calculation: (fallDistance - 3) * damageMultiplier
			// Minecraft default: 1 damage per block after 3 blocks
			double baseDamage = Math.max(0, fallDistance - 3.0);
			double expectedDamage = baseDamage * (double) damageMultiplier;

			if (expectedDamage > 0) {
				// 5% chance per point of damage (1 point = 0.5 hearts)
				double stumbleChance = Math.min(1.0, expectedDamage * 0.05);

				if (player.getRandom().nextDouble() < stumbleChance) {
					Stumble.LOGGER.info("[Stumble] Player {} STUMBLED! (Chance: {}%)",
							player.getName().getString(), (int) (stumbleChance * 100));
				} else {
					Stumble.LOGGER.info("[Stumble] Player {} fell from {} blocks. Expected Damage: {}. Stumble Chance: {}% (Safe)",
							player.getName().getString(), fallDistance, expectedDamage, (int) (stumbleChance * 100));
				}
			}
		}
	}
}
