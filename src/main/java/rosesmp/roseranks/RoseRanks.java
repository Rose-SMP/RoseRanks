package rosesmp.roseranks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rosesmp.roseranks.data.*;
import rosesmp.roseranks.services.APIService;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import java.io.IOException;
import java.util.HashMap;

public class RoseRanks implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	@Environment(EnvType.SERVER)
	public static final String MOD_ID = HalpLibe.registerMod("roseranks", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static Config config;

	public static APIService apiService;
	private static HashMap<String, User> loadedUsers;
	private static HashMap<String, Group> groups;

	@Override
	public void onInitialize() {
		apiService = new APIService();
		config = new Config();
		loadedUsers = new HashMap<>();
		groups = new HashMap<>();

		try {
			config.load();
			LOGGER.info("RoseRanks initialized. Default group: {}", config.getDefaultGroup());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void beforeGameStart() {}

	@Override
	public void afterGameStart() {}

	@Override
	public void onRecipesReady() {}

	@Override
	public void initNamespaces() {}

	public static Config getConfig() {
		return config;
	}

	public static HashMap<String, User> getLoadedUsers() {
		return loadedUsers;
	}

	public static HashMap<String, Group> getGroups() {
		return groups;
	}
}
