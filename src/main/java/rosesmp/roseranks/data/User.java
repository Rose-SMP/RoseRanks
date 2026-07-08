package rosesmp.roseranks.data;

import rosesmp.roseranks.RoseRanks;
import java.io.IOException;
import java.util.*;

/**
 * The data structure RoseRanks uses to store player information.
 */
public class User extends PermissionEntity {
	private final UUID uuid;
	private Group group;

	public User(UUID uuid) throws IOException {
		this.uuid = uuid;

		if (yamlService.userRegistered(uuid)) {
			load();
		}
		else {
			this.group = RoseRanks.defaultGroup();
		}
	}

	/**
	 * Loads a user's data from file.
	 */
	public void load() throws IOException {
		Map<String, Object> data = yamlService.load(yamlService.usersFile);
		this.group = new Group(data.get("group").toString());
	}

	/**
	 * Saves any changes made to a user to file.
	 * @return Save success
	 */
	public boolean save() throws IOException {
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("name", apiService.fetchUsername(uuid));

		return yamlService.save(yamlService.usersFile, data);
	}

	/**
	 * Grants a permission to a user.
	 * @return Grant success
	 */
	@Override public boolean grant() {
		return false;
	}

	/**
	 * Revokes a permission from a user.
	 * @return Revocation success
	 */
	@Override public boolean revoke() {
		return false;
	}

	/**
	 * Checks whether a user has a given permission.
	 * @return Whether the user has the permission
	 */
	@Override public boolean hasPermission() {
		return false;
	}

	/**
	 * Retrieves a string from a user's data.
	 * @param path The location of the desired value.
	 * @return The string at the given path.
	 */
	public String getString(String path) throws IOException {
		return yamlService.getString(yamlService.usersFile, uuid.toString() + "." + path);
	}
}
