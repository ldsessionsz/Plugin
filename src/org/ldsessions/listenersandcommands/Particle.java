package org.ldsessions.listenersandcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ldsessions.ldsessions;
import org.ldsessions.particle.ParticleEffect;
import org.ldsessions.util.MethodsUtil;

import net.minecraft.server.v1_11_R1.EnumParticle;

public class Particle implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String lebel, String[] args) {
		if (!cmd.getLabel().equalsIgnoreCase("particles"))
			return false;
		if (!(sender instanceof Player))
			return false;
		Player player = (Player) sender;
		if (args.length != 1) {
			player.sendMessage(MethodsUtil.prefix + "Please add some arguments!");
			return false;
		}
		switch (args[0].toLowerCase()) {
		case "helix":
			ParticleEffect.stop(player);
			Bukkit.getScheduler().scheduleAsyncDelayedTask(ldsessions.plugin, new Runnable() {
				public void run() {
					ParticleEffect.helix(player, EnumParticle.FLAME, EnumParticle.FLAME, .7);
				}
			}, 5);
			break;
		case "titans":
			ParticleEffect.stop(player);
			Bukkit.getScheduler().scheduleAsyncDelayedTask(ldsessions.plugin, new Runnable() {
				public void run() {
					ParticleEffect.flameoftitans(player);
				}
			}, 5);
			break;
		case "test":
			ParticleEffect.stop(player);
			Bukkit.getScheduler().scheduleAsyncDelayedTask(ldsessions.plugin, new Runnable() {
				public void run() {

				}
			}, 5);
			break;
		case "stop":
			ParticleEffect.stop(player);
			break;
		default:
			player.sendMessage(MethodsUtil.prefix + "Wrong Args");
			break;
		}
		return false;
	}

}
