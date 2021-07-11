package mc.obliviate.bloklogger.datahandler;

import mc.obliviate.bloklogger.BlokLogger;
import mc.obliviate.bloklogger.logs.logtypes.Log;

import java.util.HashMap;

public class DataHandler {

	private final HashMap<Integer, Log> logSaves = new HashMap<>();
	private final BlokLogger plugin;

	public DataHandler(final BlokLogger plugin) {
		this.plugin = plugin;
	}

	public void addLog(final Log log) {
		logSaves.put(log.getId(), log);
	}

	public void removeLog(final int id) {
		logSaves.remove(id);
	}

	public Log getLog(final int id) {
		return logSaves.get(id);
	}

}
