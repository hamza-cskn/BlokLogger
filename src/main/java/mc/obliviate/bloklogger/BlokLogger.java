package mc.obliviate.bloklogger;

import mc.obliviate.bloklogger.commands.LogCMD;
import mc.obliviate.bloklogger.database.SQLManager;
import mc.obliviate.bloklogger.datahandler.DataHandler;
import mc.obliviate.bloklogger.logs.LogManager;
import me.despical.commandframework.CommandFramework;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.efekurbann.inventory.InventoryAPI;

public class BlokLogger extends JavaPlugin {

	private final SQLManager sqlManager = new SQLManager(getDataFolder().getAbsolutePath(), this);
	private final CommandFramework commandFramework = new CommandFramework(this);
	private final InventoryAPI inventoryAPI = new InventoryAPI(this);
	private final DataHandler dataHandler = new DataHandler(this);
	private final LogManager logManager = new LogManager(this);

	@Override
	public void onEnable() {
		getLogger().info("Blok Logger v" + getDescription().getVersion() + " enabled.");
		registerCommands();
		loadManagers();
	}

	private void loadManagers() {
		sqlManager.init();
		inventoryAPI.init();
	}

	@Override
	public void onDisable() {
		sqlManager.finish();
	}

	private void registerCommands() {
		CommandFramework.ONLY_BY_CONSOLE = ChatColor.RED + "Bu komut oyuncular tarafından kullanılamaz.";
		CommandFramework.ONLY_BY_PLAYERS = ChatColor.RED + "Bu komut konsol tarafından kullanılamaz.";
		CommandFramework.NO_PERMISSION = ChatColor.RED + "Bu komutu kullanmak için yeterli izne sahip değilsiniz.";
		CommandFramework.SHORT_OR_LONG_ARG_SIZE = ChatColor.RED + "Kullandığınız komut kullanımı hatalı.";
		CommandFramework.WAIT_BEFORE_USING_AGAIN = ChatColor.RED + "Komutu tekrar kullanmadan önce biraz beklemelisiniz.";

		commandFramework.registerCommands(new LogCMD(this));
	}

	public CommandFramework getCommandFramework() {
		return commandFramework;
	}

	public SQLManager getSqlManager() {
		return sqlManager;
	}

	public DataHandler getDataHandler() {
		return dataHandler;
	}

	public LogManager getLogManager() {
		return logManager;
	}
}
