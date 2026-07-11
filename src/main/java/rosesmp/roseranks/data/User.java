package rosesmp.roseranks.data;

import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static rosesmp.roseranks.RoseRanks.*;

/**
 * The data structure RoseRanks uses to handle player information.
 */
public class User {
	private final YamlConfiguration yaml;
	private final File file;
	private final UUID uuid;
	private Group group;

	public User(UUID uuid) {
		this.uuid = uuid;

		this.file = new File(FabricLoader.getInstance().getConfigDir() + "/" + MOD_ID + "/users/" + uuid + ".yml");
		this.yaml = new YamlConfiguration(file);
	}

	/**
	 * Loads a user's data from file.
	 */
	public void load() {
		yaml.load(this.getClass());
	}

	/**
	 * Updates user's file with changes made during runtime.
	 */
	public void save() throws IOException {
		yaml.save(this);
	}

	/**
	 * Grants a permission to a user.
	 * @return Grant success.
	 */
	public boolean grant() {
		return false;
	}

	/**
	 * Revokes a permission from a user.
	 * @return Revocation success.
	 */
	public boolean revoke() {
		return false;
	}

	/**
	 * Checks whether a user has a given permission.
	 * @return Whether the user has the permission.
	 */
	public boolean hasPermission() {
		return false;
	}
}
