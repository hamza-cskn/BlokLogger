package mc.obliviate.bloklogger.logs;

import mc.obliviate.bloklogger.BlokLogger;
import mc.obliviate.bloklogger.logs.logtypes.PlayerLog;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class LogManager {


	private final BlokLogger plugin;

	public LogManager(BlokLogger plugin) {
		this.plugin = plugin;
	}

	public PlayerLog createPlayerLog(final Player player, LogReason reason) {
		return new PlayerLog(
				System.currentTimeMillis(),
				player.getUniqueId(),
				Arrays.asList(player.getInventory().getContents()),
				reason,
				plugin.getSqlManager().getLastId(),
				player.getLocation(),
				0,
				player.getExp()
		);
	}


}
