package rosesmp.roseranks.data;

import rosesmp.roseranks.services.APIService;
import rosesmp.roseranks.services.YAMLService;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The data structure RoseRanks uses to store player information.
 */
public class User extends PermissionEntity {
	private final UUID uuid;
	private final YAMLService yamlService;
	private final APIService apiService;

	public User(UUID uuid) {
		this.uuid = uuid;
		this.yamlService = plugin.yamlService;
		this.apiService = plugin.apiService;
	}

	/**
	 * Loads a user's data from file.
	 * @return Load success
	 */
	@Override public boolean load() {
		return false;
	}

	/**
	 * Saves any changes made to a user to file.
	 * @return Save success
	 */
	@Override public boolean save() throws IOException {
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("name", apiService.getUsername(uuid));

		return yamlService.save(yamlService.usersFile, data);
	}

	/**
	 * Deletes a user's RoseRanks data from the server files.
	 * @return Deletion success
	 */
	@Override public boolean delete() {
		return false;
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
