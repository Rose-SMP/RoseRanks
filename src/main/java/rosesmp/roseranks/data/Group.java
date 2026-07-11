package rosesmp.roseranks.data;

import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static rosesmp.roseranks.RoseRanks.MOD_ID;

/**
 * A group of players used for player categorization and permission allocation.
 */
public class Group {
	private final YamlConfiguration yaml;
	private final File file;
	private final String name;
	private final ArrayList<String> permissions;

	public Group(String name) {
		this.name = name;
		this.permissions = new ArrayList<>();

		this.file = new File(FabricLoader.getInstance().getConfigDir() + "/" + MOD_ID + "/groups/" + name + ".yml");
		this.yaml = new YamlConfiguration(file);
	}

	public String name() {
		return name;
	}

	/**
	 * Loads a group's data from file.
	 */
	public void load() {
		yaml.load(this.getClass());
	}

	/**
	 * Updates group's file with changes made during runtime.
	 */
	public void save() throws IOException {
		yaml.save(this);
	}

	/**
	 * Deletes a group.
	 */
	public void delete() {}

	/**
	 * Grants a permission to a group.
	 * @return Grant success.
	 */
	public boolean grant() {
		return false;
	}

	/**
	 * Revokes a permission from a group.
	 * @return Revocation success.
	 */
	public boolean revoke() {
		return false;
	}

	/**
	 * @return Whether the group has the permission.
	 */
	public boolean hasPermission() {
		return false;
	}
}
