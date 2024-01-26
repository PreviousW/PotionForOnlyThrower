package io.github.FlagFan34272.potion

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PotionSplashEvent
import org.bukkit.plugin.java.JavaPlugin

class PotionCore: JavaPlugin(), Listener {
    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onPotionSplash(event: PotionSplashEvent) {
        val thrownPotion = event.entity
        val thrower = thrownPotion.shooter

        if (thrower is Player && thrownPotion.item.type == Material.SPLASH_POTION) {
            event.affectedEntities.forEach {
                if (it.type == EntityType.PLAYER) {
                    if (it != thrower) {
                        event.setIntensity(it as LivingEntity, -1.0)
                    } else
                    if (it == thrower) {
                        for (effect in event.potion.effects) {
                            it.addPotionEffect(effect)
                        }
                    }

                }
            }
        }
    }


}