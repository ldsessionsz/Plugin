package org.ldsessions.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.ldsessions.ldsessions;

import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;

public class MethodsUtil {

	public static String prefix = ChatColor.DARK_AQUA + "[Particles]" + ChatColor.RESET;

	public static void spawnParticles(EnumParticle particleeffect, Location location, double xoffset, double yoffset,
			double zoffset, int spawncount, int speed) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particleeffect, false,
				(float) location.getX(), (float) location.getY(), (float) location.getZ(), (float) zoffset,
				(float) yoffset, (float) zoffset, (float) speed, spawncount, 0);
		for (Player p : Bukkit.getOnlinePlayers()) {
			CraftPlayer cp = (CraftPlayer) p;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
	}

	public static void spawnParticles(EnumParticle particleeffect, Location location, int spawncount, int speed) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particleeffect, false,
				(float) location.getX(), (float) location.getY(), (float) location.getZ(), (float) 0, (float) 0,
				(float) 0, speed, spawncount, 0);
		for (Player p : Bukkit.getOnlinePlayers()) {
			CraftPlayer cp = (CraftPlayer) p;
			cp.getHandle().playerConnection.sendPacket(packet);
		}
	}

	public static void toOffline(Player player) {
		if (ldsessions.online.get(player.getUniqueId()) == null) {
			new IllegalArgumentException("The player " + player.getName() + " with a UUID of "
					+ player.getUniqueId().toString() + " does not have a particle running!");
			return;
		}
		ldsessions.offline.put(player.getUniqueId(), ldsessions.online.get(player.getUniqueId()));
		ldsessions.online.remove(player.getUniqueId());
		System.out.println("The player " + player.getName() + " with a UUID of " + player.getUniqueId()
				+ " has had their particles stopped!");
		return;
	}

	public static void toOnline(Player player) {
		if (ldsessions.offline.get(player.getUniqueId()) == null) {
			new IllegalArgumentException("The player " + player.getName() + " with a UUID of "
					+ player.getUniqueId().toString() + " does not have a particle to start!");
			return;
		}
		ldsessions.online.put(player.getUniqueId(), ldsessions.offline.get(player.getUniqueId()));
		ldsessions.offline.remove(player.getUniqueId());
		startParticlesFromString(ldsessions.online.get(player.getUniqueId()), player);
		System.out.println("The player " + player.getName() + " with a UUID of " + player.getUniqueId()
				+ " has had their particles started!");
		return;
	}

	public static void startParticlesFromString(String particletype, Player player) {

		if (ldsessions.particleeffectsplayer.get(player.getUniqueId()) == null)
			return;

		switch (particletype.toLowerCase()) {
		case "helix":
			ldsessions.particleeffectsplayer.get(player.getUniqueId());
			break;
		default:
			new IllegalArgumentException("Particle type '" + particletype + "' is not a valid particle type!");
			break;
		}

	}

	public static Vector getVectorTo(Location to, Location from) {
		Vector vectorto = from.toVector().subtract(to.toVector());
		return vectorto;
	}

	// vector rotate function rights go to slikey from bukkit forums

	public static final Vector rotateAroundAxisX(Vector v, double angle) {
		double y, z, cos, sin;
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		y = v.getY() * cos - v.getZ() * sin;
		z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	public static final Vector rotateAroundAxisY(Vector v, double angle) {
		double x, z, cos, sin;
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		x = v.getX() * cos + v.getZ() * sin;
		z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
	}

	public static final Vector rotateAroundAxisZ(Vector v, double angle) {
		double x, y, cos, sin;
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		x = v.getX() * cos - v.getY() * sin;
		y = v.getX() * sin + v.getY() * cos;
		return v.setX(x).setY(y);
	}

	public static final Vector rotateVector(Vector v, double angleX, double angleY, double angleZ) {
		rotateAroundAxisX(v, angleX);
		rotateAroundAxisY(v, angleY);
		rotateAroundAxisZ(v, angleZ);
		return v;
	}

	public static final Vector rotateVector(Vector v, Location location) {
		return rotateVector(v, location.getYaw(), location.getPitch());
	}

	public static final Vector rotateVector(Vector v, float yawDegrees, float pitchDegrees) {
		double yaw = Math.toRadians(-1 * (yawDegrees + 90));
		double pitch = Math.toRadians(-pitchDegrees);

		double cosYaw = Math.cos(yaw);
		double cosPitch = Math.cos(pitch);
		double sinYaw = Math.sin(yaw);
		double sinPitch = Math.sin(pitch);

		double initialX, initialY, initialZ;
		double x, y, z;

		initialX = v.getX();
		initialY = v.getY();
		x = initialX * cosPitch - initialY * sinPitch;
		y = initialX * sinPitch + initialY * cosPitch;

		initialZ = v.getZ();
		initialX = x;
		z = initialZ * cosYaw - initialX * sinYaw;
		x = initialZ * sinYaw + initialX * cosYaw;

		return new Vector(x, y, z);
	}

}
