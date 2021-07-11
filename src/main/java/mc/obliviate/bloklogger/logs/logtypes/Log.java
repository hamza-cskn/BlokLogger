package mc.obliviate.bloklogger.logs.logtypes;

import mc.obliviate.bloklogger.logs.LogReason;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public interface Log {

	LogReason getReason();
	Long getDate();
	Collection<ItemStack> getItems();
	int getId();

}
