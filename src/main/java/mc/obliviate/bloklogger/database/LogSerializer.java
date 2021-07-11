package mc.obliviate.bloklogger.database;

import mc.obliviate.bloklogger.logs.LogReason;
import mc.obliviate.bloklogger.logs.logtypes.PlayerLog;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class LogSerializer {

	protected static PlayerLog serializePlayerLog(final ResultSet rs) throws SQLException {

		final long date = deserializeDate(rs.getString("date"));
		final UUID uuid = deserializeUUID(rs.getString("player"));
		final List<ItemStack> items = deserializeItemStacks(rs.getString("items"));
		final LogReason reason = deserializeLogReason(rs.getString("reason"));
		final int id = deserializeId(rs.getInt("id"));
		final Location location = deserializeLocation(rs.getString("location"));
		final double money = deserializeMoney(rs.getString("money"));
		final double xp  = deserializeMoney(rs.getString("xp"));

		return new PlayerLog(date, uuid, items, reason, id, location, money, xp);
	}

	private static long deserializeDate(final String string) {
		try {
			return Long.parseLong(string);
		} catch (NumberFormatException e) {
			Bukkit.getLogger().severe("[BlokLogger] Could not deserialize date: " + string);
			return 0;
		}
	}

	private static UUID deserializeUUID(final String string) {
		return UUID.fromString(string);
	}

	private static List<ItemStack> deserializeItemStacks(final String base64String) {
		try {
			return new ArrayList<>(Arrays.asList(BukkitInventorySerializer.fromBase64(base64String).getContents()));
		} catch (IOException e) {
			Bukkit.getLogger().severe("[BlokLogger] Could not deserialize inventory: " + base64String.substring(0, 50) + " (" + base64String.length() + " char)");
			e.printStackTrace();
		}
		return null;
	}

	private static LogReason deserializeLogReason(final String string) {
		return LogReason.valueOf(string);
	}

	private static int deserializeId(final int id) {
		return id;
	}

	private static Location deserializeLocation(final String string) {
		final String[] data = string.split(",");
		return new Location(Bukkit.getWorld(data[0]),
				Double.parseDouble(data[1]),
				Double.parseDouble(data[2]),
				Double.parseDouble(data[3]),
				Float.parseFloat(data[4]),
				Float.parseFloat(data[5]));
	}

	private static double deserializeMoney(final String string) {
		try {
			return Double.parseDouble(string);
		} catch (NumberFormatException e) {
			return Double.NaN;
		}
	}
	private static double deserializeXp(final String string) {
		try {
			return Double.parseDouble(string);
		} catch (NumberFormatException e) {
			return Double.NaN;
		}
	}


}
