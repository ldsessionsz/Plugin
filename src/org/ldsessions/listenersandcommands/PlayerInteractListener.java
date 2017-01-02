package org.ldsessions.listenersandcommands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.ldsessions.particle.ParticleEffect;

import net.minecraft.server.v1_11_R1.EnumParticle;

public class PlayerInteractListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		ItemStack item = event.getItem();
		Player player = event.getPlayer();
		if (item.getType() == Material.DIAMOND_SWORD
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getClickedBlock().getType().isSolid() == false)
				&& player.getExpToLevel() >= 10) {
			ParticleEffect.killerLazer(player, EnumParticle.END_ROD, EnumParticle.END_ROD, EnumParticle.DRAGON_BREATH,
					0.25, (long) 1);
			player.giveExpLevels(-10);
			return;
		} else {
			// ParticleEffect.bloodHelix(player);
			return;
		}
	}

}
