package org.ldsessions.particle;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.ldsessions.ldsessions;
import org.ldsessions.util.MethodsUtil;

import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;

public class ParticleEffect {

	@SuppressWarnings({ "unused" })
	public static void helix(Player player, EnumParticle particle1, EnumParticle particle2, double radius) {
		ldsessions.online.remove(player.getUniqueId());
		ldsessions.online.put(player.getUniqueId(), "helix");
		BukkitTask loop = new BukkitRunnable() {
			double t = 0;
			double k = 0;
			double r = radius;
			Location location = new Location(player.getWorld(), player.getLocation().getX(),
					player.getLocation().getY(), player.getLocation().getZ());
			int q = 0;

			@SuppressWarnings("deprecation")
			public void run() {
				Location loc = player.getLocation();
				if (location.equals(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()))) {
					double x = r * Math.sin(t);
					double y = k;
					double z = r * Math.cos(t);
					double x2 = -r * Math.sin(t);
					double y2 = k;
					double z2 = -r * Math.cos(t);
					loc.add(x, y, z);
					MethodsUtil.spawnParticles(particle1, loc, 1, 0);
					loc.subtract(x, y, z);
					loc.add(x2, y2, z2);
					MethodsUtil.spawnParticles(particle2, loc, 1, 0);
					loc.subtract(x2, y2, z2);
					switch (q) {
					case 0:
						k = k + Math.PI / 64;
						break;
					case 1:
						k = k - Math.PI / 64;
						break;
					case 2:

						break;
					default:

						break;
					}
					t = t + Math.PI / 16;
					if (k >= 2.20) {
						k = 2.19999999;
						q = 2;
						Bukkit.getScheduler().scheduleAsyncDelayedTask(ldsessions.plugin, new Runnable() {
							public void run() {
								q = 1;
							}
						}, 15);
					}
					if (k <= 0) {
						k = .000001;
						q = 2;
						Bukkit.getScheduler().scheduleAsyncDelayedTask(ldsessions.plugin, new Runnable() {
							public void run() {
								q = 0;
							}
						}, 10);
					}
					loc = player.getLocation();
					location = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
				} else if (!location.equals(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()))) {
					k = 0;
					q = 0;

					double x = 0.5 * Math.sin(t + loc.getYaw());
					double y = 0.5 * Math.cos(t) + 1;
					double z = 0;
					MethodsUtil.spawnParticles(particle1, loc, 1, (int) 0.03);
					loc.subtract(x, y, z);
					t = t + Math.PI / 16;
					loc = player.getLocation();
					location = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
				}
				if (!(ldsessions.online.get(player.getUniqueId()) == "helix")) {
					Bukkit.getScheduler().cancelTask(this.getTaskId());
				}
			}

		}.runTaskTimer(ldsessions.plugin, 0, 1);
	}

	@SuppressWarnings("unused")
	public static void flameoftitans(Player player) {
		ldsessions.online.remove(player.getUniqueId());
		ldsessions.online.put(player.getUniqueId(), "flameoftitans");
		player.sendMessage(MethodsUtil.prefix + " You have started Flame of the Titans!");
		BukkitTask loop = new BukkitRunnable() {
			double r = 2.75;
			double k = 0.2;
			double positivet = 0;
			double negativet = 0;
			Location location = new Location(player.getWorld(), player.getLocation().getX(),
					player.getLocation().getY(), player.getLocation().getZ());
			int q = 1;

			public void run() {
				Location loc = player.getLocation();
				if (location.equals(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()))) {
					Location playerlocplus2 = loc.add(0, 0.2, 0);
					double x1 = r * Math.sin(positivet);
					double y1 = k;
					double z1 = r * Math.cos(positivet);
					double x2 = -r * Math.sin(positivet);
					double y2 = k;
					double z2 = -r * Math.cos(positivet);
					double x3 = r * Math.sin(positivet + 77);
					double y3 = k;
					double z3 = r * Math.cos(positivet + 77);
					double x4 = -r * Math.sin(positivet + 77);
					double y4 = k;
					double z4 = -r * Math.cos(positivet + 77);
					Location loc1 = new Location(loc.getWorld(), loc.getX() + x1, loc.getY() + y1, loc.getZ() + z1);
					Location loc2 = new Location(loc.getWorld(), loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2);
					Location loc3 = new Location(loc.getWorld(), loc.getX() + x3, loc.getY() + y3, loc.getZ() + z3);
					Location loc4 = new Location(loc.getWorld(), loc.getX() + x4, loc.getY() + y4, loc.getZ() + z4);
					Vector vec1 = MethodsUtil.getVectorTo(loc1, playerlocplus2);
					Vector vec2 = MethodsUtil.getVectorTo(loc2, playerlocplus2);
					Vector vec3 = MethodsUtil.getVectorTo(loc3, playerlocplus2);
					Vector vec4 = MethodsUtil.getVectorTo(loc4, playerlocplus2);
					PacketPlayOutWorldParticles packet1 = new PacketPlayOutWorldParticles(EnumParticle.FLAME, false,
							(float) loc1.getX(), (float) loc1.getY(), (float) loc1.getZ(), (float) vec1.getX(),
							(float) vec1.getY(), (float) vec1.getZ(), (float) 0.055, 0, 0);
					PacketPlayOutWorldParticles packet2 = new PacketPlayOutWorldParticles(EnumParticle.FLAME, false,
							(float) loc2.getX(), (float) loc2.getY(), (float) loc2.getZ(), (float) vec2.getX(),
							(float) vec2.getY(), (float) vec2.getZ(), (float) 0.055, 0, 0);
					PacketPlayOutWorldParticles packet3 = new PacketPlayOutWorldParticles(EnumParticle.FLAME, false,
							(float) loc3.getX(), (float) loc3.getY(), (float) loc3.getZ(), (float) vec3.getX(),
							(float) vec3.getY(), (float) vec3.getZ(), (float) 0.055, 0, 0);
					PacketPlayOutWorldParticles packet4 = new PacketPlayOutWorldParticles(EnumParticle.FLAME, false,
							(float) loc4.getX(), (float) loc4.getY(), (float) loc4.getZ(), (float) vec4.getX(),
							(float) vec4.getY(), (float) vec4.getZ(), (float) 0.055, 0, 0);
					double x5 = r * Math.sin(negativet);
					double y5 = k;
					double z5 = r * Math.cos(negativet);
					double x6 = -r * Math.sin(negativet);
					double y6 = k;
					double z6 = -r * Math.cos(negativet);
					double x7 = r * Math.sin(negativet + 77);
					double y7 = k;
					double z7 = r * Math.cos(negativet + 77);
					double x8 = -r * Math.sin(negativet + 77);
					double y8 = k;
					double z8 = -r * Math.cos(negativet + 77);
					Location loc5 = new Location(loc.getWorld(), loc.getX() + x5, loc.getY() + y5, loc.getZ() + z5);
					Location loc6 = new Location(loc.getWorld(), loc.getX() + x6, loc.getY() + y6, loc.getZ() + z6);
					Location loc7 = new Location(loc.getWorld(), loc.getX() + x7, loc.getY() + y7, loc.getZ() + z7);
					Location loc8 = new Location(loc.getWorld(), loc.getX() + x8, loc.getY() + y8, loc.getZ() + z8);
					Vector vec5 = MethodsUtil.getVectorTo(loc5, playerlocplus2);
					Vector vec6 = MethodsUtil.getVectorTo(loc6, playerlocplus2);
					Vector vec7 = MethodsUtil.getVectorTo(loc7, playerlocplus2);
					Vector vec8 = MethodsUtil.getVectorTo(loc8, playerlocplus2);
					PacketPlayOutWorldParticles packet5 = new PacketPlayOutWorldParticles(EnumParticle.FLAME, false,
							(float) loc5.getX(), (float) loc5.getY(), (float) loc5.getZ(), (float) vec5.getX(),
							(float) vec5.getY(), (float) vec5.getZ(), (float) 0.055, 0, 0);
					PacketPlayOutWorldParticles packet6 = new PacketPlayOutWorldParticles(EnumParticle.FLAME, false,
							(float) loc6.getX(), (float) loc6.getY(), (float) loc6.getZ(), (float) vec6.getX(),
							(float) vec6.getY(), (float) vec6.getZ(), (float) 0.055, 0, 0);
					PacketPlayOutWorldParticles packet7 = new PacketPlayOutWorldParticles(EnumParticle.FLAME, false,
							(float) loc7.getX(), (float) loc7.getY(), (float) loc7.getZ(), (float) vec7.getX(),
							(float) vec7.getY(), (float) vec7.getZ(), (float) 0.055, 0, 0);
					PacketPlayOutWorldParticles packet8 = new PacketPlayOutWorldParticles(EnumParticle.FLAME, false,
							(float) loc8.getX(), (float) loc8.getY(), (float) loc8.getZ(), (float) vec8.getX(),
							(float) vec8.getY(), (float) vec8.getZ(), (float) 0.055, 0, 0);
					CraftPlayer p = (CraftPlayer) player;
					p.getHandle().playerConnection.sendPacket(packet1);
					p.getHandle().playerConnection.sendPacket(packet2);
					p.getHandle().playerConnection.sendPacket(packet3);
					p.getHandle().playerConnection.sendPacket(packet4);
					p.getHandle().playerConnection.sendPacket(packet5);
					p.getHandle().playerConnection.sendPacket(packet6);
					p.getHandle().playerConnection.sendPacket(packet7);
					p.getHandle().playerConnection.sendPacket(packet8);
					positivet += Math.PI / 32;
					negativet -= Math.PI / 32;
					loc = player.getLocation();
					location = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
				} else {
					q = 0;
					loc = player.getLocation();
					location = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
				}
				if (!(ldsessions.online.get(player.getUniqueId()) == "flameoftitans")) {
					this.cancel();
				}
			}

		}.runTaskTimer(ldsessions.plugin, 0, 2);
	}

	@SuppressWarnings("unused")
	public static void killerLazer(Player player, EnumParticle particle1, EnumParticle particle2,
			EnumParticle particle3, double radius, long period) {
		ldsessions.online.remove(player.getUniqueId());
		ldsessions.online.put(player.getUniqueId(), "test");
		BukkitTask loop = new BukkitRunnable() {
			double r = radius;
			double t = 0;
			Location loc = player.getLocation().add(0, player.getEyeHeight(), 0);

			@SuppressWarnings("deprecation")
			public void run() {
				Location location = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(),
						loc.getPitch() + 90);
				// for (double k = 0; k <= 60; k += Math.PI / 6) {
				double x1 = r * Math.sin(t);
				double y1 = t / 3;
				double z1 = r * Math.cos(t);
				double x2 = -r * Math.sin(t);
				double y2 = t / 3;
				double z2 = -r * Math.cos(t);
				double x3 = 0;
				double y3 = t / 3;
				double z3 = 0;
				double x4 = -r * Math.sin(t + 77);
				double y4 = t / 3;
				double z4 = -r * Math.cos(t + 77);
				double x5 = r * Math.sin(t + 77);
				double y5 = t / 3;
				double z5 = r * Math.cos(t + 77);
				Vector vec1 = new Vector(x1, y1, z1);
				Vector vec2 = new Vector(x2, y2, z2);
				Vector vec3 = new Vector(x3, y3, z3);
				Vector vec4 = new Vector(x4, y4, z4);
				Vector vec5 = new Vector(x5, y5, z5);
				Vector vecloc1 = MethodsUtil.rotateVector(vec1, location);
				Vector vecloc2 = MethodsUtil.rotateVector(vec2, location);
				Vector vecloc3 = MethodsUtil.rotateVector(vec3, location);
				Vector vecloc4 = MethodsUtil.rotateVector(vec4, location);
				Vector vecloc5 = MethodsUtil.rotateVector(vec5, location);
				Location particleloc1 = new Location(loc.getWorld(), loc.getX() + vecloc1.getX(),
						loc.getY() + vecloc1.getY(), loc.getZ() + vecloc1.getZ());
				Location particleloc2 = new Location(loc.getWorld(), loc.getX() + vecloc2.getX(),
						loc.getY() + vecloc2.getY(), loc.getZ() + vecloc2.getZ());
				Location particleloc3 = new Location(loc.getWorld(), loc.getX() + vecloc3.getX(),
						loc.getY() + vecloc3.getY(), loc.getZ() + vecloc3.getZ());
				Location particleloc4 = new Location(loc.getWorld(), loc.getX() + vecloc4.getX(),
						loc.getY() + vecloc4.getY(), loc.getZ() + vecloc4.getZ());
				Location particleloc5 = new Location(loc.getWorld(), loc.getX() + vecloc5.getX(),
						loc.getY() + vecloc5.getY(), loc.getZ() + vecloc5.getZ());
				PacketPlayOutWorldParticles packet1 = new PacketPlayOutWorldParticles(particle1, false,
						(float) particleloc1.getX(), (float) particleloc1.getY(), (float) particleloc1.getZ(), 0F, 0F,
						0F, 0, 3, 0);
				PacketPlayOutWorldParticles packet2 = new PacketPlayOutWorldParticles(particle2, false,
						(float) particleloc2.getX(), (float) particleloc2.getY(), (float) particleloc2.getZ(), 0F, 0F,
						0F, 0, 3, 0);
				PacketPlayOutWorldParticles packet3 = new PacketPlayOutWorldParticles(particle3, false,
						(float) particleloc3.getX(), (float) particleloc3.getY(), (float) particleloc3.getZ(), 0F, 0F,
						0F, 0, 3, 0);
				PacketPlayOutWorldParticles packet4 = new PacketPlayOutWorldParticles(particle2, false,
						(float) particleloc4.getX(), (float) particleloc4.getY(), (float) particleloc4.getZ(), 0F, 0F,
						0F, 0, 3, 0);
				PacketPlayOutWorldParticles packet5 = new PacketPlayOutWorldParticles(particle1, false,
						(float) particleloc5.getX(), (float) particleloc5.getY(), (float) particleloc5.getZ(), 0F, 0F,
						0F, 0, 3, 0);
				if (particleloc3.getBlock().getType().isTransparent() == false) {
					loc.getWorld().playSound(particleloc3, Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);
					particleloc3.getWorld().playSound(particleloc3, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
					EntityDamageEvent e = new EntityDamageEvent(player, DamageCause.MELTING, 20D);
					for (Entity ent : particleloc3.getWorld().getNearbyEntities(particleloc3, 1, 2, 1)) {
						if (!(ent instanceof Animals || ent instanceof Monster || ent instanceof Villager
								|| ent instanceof Player))
							return;
						burst(ent.getLocation(), true);
						ent.setLastDamageCause(e);
						ent.getWorld().playSound(particleloc3, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
						if (ent instanceof Animals) {
							((Animals) ent).damage(2D);
						}
						if (ent instanceof Monster) {
							((Monster) ent).damage(4D);
						}
						if (ent instanceof Villager) {
							((Villager) ent).damage(2);
						}
						if (ent instanceof Player) {
							((Player) ent).damage(5D);
						}
					}
					burst(particleloc3, false);
					this.cancel();
				}
				for (Player p : Bukkit.getOnlinePlayers()) {
					CraftPlayer cp = (CraftPlayer) p;
					cp.getHandle().playerConnection.sendPacket(packet1);
					cp.getHandle().playerConnection.sendPacket(packet2);
					cp.getHandle().playerConnection.sendPacket(packet3);
					// cp.getHandle().playerConnection.sendPacket(packet4);
					// cp.getHandle().playerConnection.sendPacket(packet5);
				}
				if (!particleloc3.getWorld().getNearbyEntities(particleloc3, 0.25, 1, .25).isEmpty()) {
					for (Entity entity : particleloc3.getWorld().getNearbyEntities(particleloc3, 0.25, 1, .25)) {
						if (particleloc3.distance(entity.getLocation().add(0, 1, 0)) <= .75
								&& entity.getType() != EntityType.PLAYER) {
							burst(entity.getLocation(), true);
							EntityDamageEvent e = new EntityDamageEvent(player, DamageCause.MELTING, 20D);
							entity.setLastDamageCause(e);
							entity.getWorld().strikeLightningEffect(entity.getLocation()).setSilent(true);
							particleloc3.getWorld().playSound(particleloc3, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
							if (entity instanceof Animals) {
								((Animals) entity).damage(20D);
							}
							if (entity instanceof Monster) {
								((Monster) entity).damage(20000000D);
							}
							if (entity instanceof Villager) {
								((Villager) entity).damage(20);
							}
							this.cancel();
						}
					}
				}
				// }
				t += Math.PI / 6;
				// r = r - (r / 60);
				if (t >= 60) {
					burst(particleloc1, false);
					burst(particleloc2, false);
					burst(particleloc3, false);
					EntityDamageEvent e = new EntityDamageEvent(player, DamageCause.MELTING, 20D);
					for (Entity ent : particleloc3.getWorld().getNearbyEntities(particleloc3, 1, 1, 1)) {
						if (!(ent instanceof Animals || ent instanceof Monster || ent instanceof Villager
								|| ent instanceof Player))
							return;
						burst(ent.getLocation(), true);
						ent.setLastDamageCause(e);
						ent.getWorld().playSound(particleloc3, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
						if (ent instanceof Animals) {
							((Animals) ent).damage(2D);
						}
						if (ent instanceof Monster) {
							((Monster) ent).damage(4D);
						}
						if (ent instanceof Villager) {
							((Villager) ent).damage(2);
						}
						if (ent instanceof Player) {
							((Player) ent).damage(5D);
						}
					}
					particleloc3.getWorld().playSound(particleloc3, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
					this.cancel();
				}
			}
		}.runTaskTimer(ldsessions.plugin, 0, period);
	}

	@SuppressWarnings("unused")
	public static void bloodHelix(Player player) {
		BukkitTask loop = new BukkitRunnable() {

			double e = 0;

			public void run() {
				Location loc = player.getLocation();
				double k = 1;
				for (double t = 0; t <= 60; t += Math.PI / 16) {
					double x = k * Math.sin(t + e);
					double y = t / 1.75;
					double z = k * Math.cos(t + e);
					double x2 = -k * Math.sin(t + e);
					double y2 = t / 1.75;
					double z2 = -k * Math.cos(t + e);
					double x3 = -k * Math.sin(t + 77 + e);
					double y3 = t / 1.75;
					double z3 = -k * Math.cos(t + 77 + e);
					double x4 = k * Math.sin(t + 77 + e);
					double y4 = t / 1.75;
					double z4 = k * Math.cos(t + 77 + e);
					k -= Math.PI / 80;
					if (k <= 0) {
						t = 60;
					}
					MethodsUtil.spawnParticles(EnumParticle.REDSTONE,
							new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z), 0, 0);
					MethodsUtil.spawnParticles(EnumParticle.REDSTONE,
							new Location(loc.getWorld(), loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2), 0, 0);
					MethodsUtil.spawnParticles(EnumParticle.REDSTONE,
							new Location(loc.getWorld(), loc.getX() + x3, loc.getY() + y3, loc.getZ() + z3), 0, 0);
					MethodsUtil.spawnParticles(EnumParticle.REDSTONE,
							new Location(loc.getWorld(), loc.getX() + x4, loc.getY() + y4, loc.getZ() + z4), 0, 0);
				}
				e += Math.PI / 4;
			}
		}.runTaskTimer(ldsessions.plugin, 0, 10);
	}

	public static void burst(Location location, boolean withhelix) {
		for (double r = 0; r <= 15; r++) {
			Vector vec = new Vector();
			vec.setX((0.0D + Math.random() - Math.random()));
			vec.setY(Math.random());
			vec.setZ((0.0D + Math.random() - Math.random()));
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FIREWORKS_SPARK, false,
					(float) location.getX(), (float) location.getY(), (float) location.getZ(), (float) vec.getX(),
					(float) vec.getY(), (float) vec.getZ(), (float) .5, 0, 5);
			PacketPlayOutWorldParticles packet2 = new PacketPlayOutWorldParticles(EnumParticle.EXPLOSION_NORMAL, false,
					(float) location.getX(), (float) location.getY(), (float) location.getZ(), (float) vec.getX(),
					(float) vec.getY(), (float) vec.getZ(), (float) .5, 0, 5);
			for (Player p : Bukkit.getOnlinePlayers()) {
				CraftPlayer cp = (CraftPlayer) p;
				cp.getHandle().playerConnection.sendPacket(packet);
				cp.getHandle().playerConnection.sendPacket(packet2);
			}
		}
		double k = 1;
		if (withhelix != true)
			return;
		for (double t = 0; t <= 60; t += Math.PI / 16) {
			double x = k * Math.sin(t);
			double y = t / 2;
			double z = k * Math.cos(t);
			double x2 = -k * Math.sin(t);
			double y2 = t / 2;
			double z2 = -k * Math.cos(t);
			double x3 = -k * Math.sin(t + 77);
			double y3 = t / 2;
			double z3 = -k * Math.cos(t + 77);
			double x4 = k * Math.sin(t + 77);
			double y4 = t / 2;
			double z4 = k * Math.cos(t + 77);
			k -= Math.PI / 128;
			if (k <= 0) {
				t = 60;
			}
			MethodsUtil.spawnParticles(EnumParticle.CLOUD,
					new Location(location.getWorld(), location.getX() + x, location.getY() + y, location.getZ() + z), 1,
					0);
			MethodsUtil.spawnParticles(EnumParticle.CLOUD,
					new Location(location.getWorld(), location.getX() + x2, location.getY() + y2, location.getZ() + z2),
					1, 0);
			MethodsUtil.spawnParticles(EnumParticle.CLOUD,
					new Location(location.getWorld(), location.getX() + x3, location.getY() + y3, location.getZ() + z3),
					1, 0);
			MethodsUtil.spawnParticles(EnumParticle.CLOUD,
					new Location(location.getWorld(), location.getX() + x4, location.getY() + y4, location.getZ() + z4),
					1, 0);
		}
	}

	public static void stop(Player player) {
		ldsessions.online.remove(player.getUniqueId());
	}

}
