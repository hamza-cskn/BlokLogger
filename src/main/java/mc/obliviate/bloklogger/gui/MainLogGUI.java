package mc.obliviate.bloklogger.gui;

import mc.obliviate.bloklogger.logs.logtypes.Log;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import xyz.efekurbann.inventory.GUI;
import xyz.efekurbann.inventory.Hytem;

import java.util.List;

public class MainLogGUI extends GUI {

	private Player player;
	private final List<Log> logs;

	public MainLogGUI(List<Log> logs) {
		super("main-log-gui", "Logların", 54);
		this.logs = logs;
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {
		player = (Player) event.getPlayer();

		int i = 0;
		for (final Log log : logs) {
			addItem(i++, new Hytem(new ItemStack(Material.ENDER_CHEST), e-> {
				new LogViewGUI(log).open((Player) e.getWhoClicked());
			}));
		}

	}
}
