package mc.obliviate.bloklogger.database;

import mc.obliviate.bloklogger.BlokLogger;
import mc.obliviate.bloklogger.logs.LogReason;
import mc.obliviate.bloklogger.logs.logtypes.Log;
import mc.obliviate.bloklogger.logs.logtypes.PlayerLog;
import mc.obliviate.bloksqliteapi.SQLHandler;
import mc.obliviate.bloksqliteapi.sqlutils.DataType;
import mc.obliviate.bloksqliteapi.sqlutils.SQLTable;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLManager extends SQLHandler {

	private final BlokLogger plugin;
	private SQLTable table;

	public SQLManager(String filePath, BlokLogger plugin) {
		super(filePath, true);
		this.plugin = plugin;
	}

	public void init() {
		connect("database");
	}

	public void finish() {
		try {
			getConnection().close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void connect(String databaseName) {
		super.connect(databaseName);
	}

	@Override
	public void onConnect() {
		createTable();
		if (table == null) {
			throw new IllegalStateException("SQL Table could not created!");
		}
		plugin.getLogger().info("SQL Table confirmed.");
		Player player = Bukkit.getPlayer("Mr_Obliviate");
		addLog(plugin.getLogManager().createPlayerLog(player, LogReason.MANUEL));
		player.sendMessage("loged");

	}

	public List<Log> getPlayerLogs(OfflinePlayer player) {
		final List<Log> result = new ArrayList<>();
		final ResultSet rs = table.select("player", player.getUniqueId().toString());
		try {
			while (rs.next()) {
				result.add(LogSerializer.serializePlayerLog(rs));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	public void createTable() {
		table = new SQLTable("logs", "id")
				.addField("id", DataType.INTEGER, true, true, true)
				.addField("player", DataType.TEXT)
				.addField("date", DataType.TEXT)
				.addField("reason", DataType.TEXT)
				.addField("items", DataType.TEXT)
				.addField("location", DataType.TEXT)
				.addField("extra_data", DataType.TEXT)
				.create();
	}

	public int getLastId() {
		final Object lastId = table.getSingleHighest("id");
		return (lastId == null ? 0 : Integer.parseInt(lastId.toString()) + 1);
	}

	public void addLog(final PlayerLog playerLog) {
		final ItemStack[] items = new ItemStack[playerLog.getItems().size()];
		int i = 0;
		for (ItemStack item : playerLog.getItems()) {
			items[i++] = item;
		}
		table.insert(table.createUpdate(playerLog.getId())
				.putData("id", playerLog.getId())
				.putData("player", playerLog.getPlayerUniqueId())
				.putData("date", playerLog.getDate().toString())
				.putData("reason", playerLog.getReason().name())
				.putData("items", items)
				.putData("location", playerLog.getLocation().toString())
				.putData("extra_data", "aleyküm selam"));
	}
}
