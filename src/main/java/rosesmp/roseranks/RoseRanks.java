package rosesmp.roseranks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rosesmp.roseranks.data.*;
import rosesmp.roseranks.services.APIService;
import rosesmp.roseranks.services.YAMLService;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.io.IOException;
import java.util.ArrayList;

public class RoseRanks implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	@Environment(EnvType.SERVER)
	public static final String MOD_ID = HalpLibe.registerMod("roseranks", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public Config config;
	public static YAMLService yamlService;
	public static APIService apiService;

	private static ArrayList<User> users;

	@Override
	public void onInitialize() {
		config = new Config();
		users = new ArrayList<>();

		LOGGER.info("RoseRanks initialized.");
	}

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}

	/**
	 * @return The list of loaded users
	 */
	public static ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @return The configured default group
	 */
	public static Group defaultGroup() throws IOException {
		String name = yamlService.getString(yamlService.configFile, "default-group");
		return new Group(name);
	}
}
