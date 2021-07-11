package mc.obliviate.bloklogger.commands;

import mc.obliviate.bloklogger.BlokLogger;
import mc.obliviate.bloklogger.gui.LogViewGUI;
import mc.obliviate.bloklogger.gui.MainLogGUI;
import mc.obliviate.bloklogger.logs.LogReason;
import mc.obliviate.bloksqliteapi.SQLHandler;
import me.despical.commandframework.Command;
import me.despical.commandframework.CommandArguments;
import me.despical.commandframework.Completer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class LogCMD {

	private final BlokLogger plugin;

	public LogCMD(BlokLogger plugin) {
		this.plugin = plugin;
	}

	@Command(name = "bloklogger",
			aliases = "bl",
			desc = "The plugin's main command.",
			permission = "bloklogger.admin",
			senderType = Command.SenderType.PLAYER)
	public void loggerCommand(CommandArguments arguments) {
		final Player player = arguments.getSender();
		final int args = arguments.getArguments().length;

		if (args == 0) {
			player.sendMessage("/bl menu");
			player.sendMessage("/bl log");
		} else if (args == 1) {
			if (arguments.getArgument(0).equalsIgnoreCase("log")) {
				plugin.getSqlManager().addLog(plugin.getLogManager().createPlayerLog(player, LogReason.MANUEL));
				if (true) return;
				player.sendMessage("loged");
			} else if (arguments.getArgument(0).equalsIgnoreCase("menu")) {
				new MainLogGUI(plugin.getSqlManager().getPlayerLogs(player)).open(player);
			}
		} else {

		}

	}

	@Completer(name = "bloklogger", aliases = "bl")
	public List<String> loggerCommandCompletion(CommandArguments arguments) {
		if (arguments.getSender() instanceof Player && arguments.getSender().isOp()) {
			return Arrays.asList("");
		}
		return Arrays.asList("");
	}


}
