package mc.obliviate.bloklogger.logs.logtypes;

import mc.obliviate.bloklogger.logs.LogReason;
import org.bukkit.Location;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class DeathLog extends PlayerLog{

	private final EntityDamageEvent.DamageCause deathCause;

	public DeathLog(final Long date,
	                final UUID player,
	                final List<ItemStack> item,
	                final LogReason reason,
	                final int id,
	                final Location location,
	                final double money,
	                final double xp,
	                final EntityDamageEvent.DamageCause deathCause) {
		super(date, player, item, reason, id, location, money, xp);
		this.deathCause = deathCause;
	}

	public EntityDamageEvent.DamageCause getDeathCause() {
		return deathCause;
	}
}
