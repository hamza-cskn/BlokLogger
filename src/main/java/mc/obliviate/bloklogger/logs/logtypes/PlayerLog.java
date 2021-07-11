package mc.obliviate.bloklogger.logs.logtypes;

import jdk.nashorn.internal.objects.annotations.Getter;
import mc.obliviate.bloklogger.logs.LogReason;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class PlayerLog implements Log {

	private final Long date;
	private final UUID player;
	private final List<ItemStack> item;
	private final LogReason reason;
	private final int id;
	private final Location location;
	private final double money;
	private final double xp;

	public PlayerLog(final Long date,
	                 final  UUID player,
	                 final  List<ItemStack> item,
	                 final LogReason reason,
	                 final int id,
	                 final Location location,
	                 final double money,
	                 final double xp
	) {
		this.date = date;
		this.player = player;
		this.item = item;
		this.reason = reason;
		this.id = id;
		this.location = location;
		this.money = money;
		this.xp = xp;
	}

	public Long getDate() {
		return date;
	}

	public UUID getPlayerUniqueId() {
		return player;
	}

	public List<ItemStack> getItems() {
		return item;
	}

	public LogReason getReason() {
		return reason;
	}

	public int getId() {
		return id;
	}

	public Location getLocation() {
		return location;
	}

	public UUID getPlayer() {
		return player;
	}
}
