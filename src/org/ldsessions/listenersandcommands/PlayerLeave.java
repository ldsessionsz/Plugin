package org.ldsessions.listenersandcommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.ldsessions.ldsessions;
import org.ldsessions.util.MethodsUtil;

public class PlayerLeave implements Listener {

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		if (ldsessions.online.containsKey(event.getPlayer().getUniqueId()) == false)
			return;
		MethodsUtil.toOffline(event.getPlayer());
	}

}
