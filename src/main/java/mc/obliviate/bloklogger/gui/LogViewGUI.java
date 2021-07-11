package mc.obliviate.bloklogger.gui;

import mc.obliviate.bloklogger.logs.logtypes.Log;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import xyz.efekurbann.inventory.GUI;
import xyz.efekurbann.inventory.Hytem;

public class LogViewGUI extends GUI {

	private final Log log;

	public LogViewGUI(Log log) {
		super("log-view-gui", "Log kayıdı: " + log.getId(), 54);
		this.log = log;
	}


	@Override
	public void onOpen(InventoryOpenEvent event) {
		int i = 0;
		for (; i < 9; i++) {
			addItem(i, new Hytem(Material.STAINED_GLASS_PANE).setDamage((byte) 15));
		}

		for (final ItemStack item : log.getItems()) {
			addItem(i++, new Hytem(item, e -> {
				e.setCancelled(false);
			}));
		}

	}
}
