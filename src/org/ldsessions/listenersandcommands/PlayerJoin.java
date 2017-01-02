package org.ldsessions.listenersandcommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.ldsessions.ldsessions;
import org.ldsessions.util.MethodsUtil;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (ldsessions.offline.containsKey(event.getPlayer().getUniqueId()) == false)
			return;
		MethodsUtil.toOnline(event.getPlayer());
	}

}
