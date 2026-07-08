package rosesmp.roseranks.data;

import rosesmp.roseranks.services.APIService;
import rosesmp.roseranks.services.YAMLService;
import java.io.IOException;

/**
 * A group of players used for player categorization and permission allocation.
 */
public class Group extends PermissionEntity {
	private final String name;
	private final YAMLService yamlService;
	private final APIService apiService;

	public Group(String name) {
		this.name = name;
		this.yamlService = plugin.yamlService;
		this.apiService = plugin.apiService;
	}

	/**
	 * Loads a group's data from file.
	 * @return Load success
	 */
	@Override public boolean load() {
		return false;
	}

	/**
	 * Saves any changes made to a group to file.
	 * @return Save success
	 */
	@Override public boolean save() {
		//Map<String, Object> data = new LinkedHashMap<>();
		//return yamlService.save(yamlService.groupsFile, data);
		return false;
	}

	/**
	 * Deletes a group.
	 * @return Deletion success
	 */
	@Override public boolean delete() {
		return false;
	}

	/**
	 * Grants a permission to a group.
	 * @return Grant success
	 */
	@Override public boolean grant() {
		return false;
	}

	/**
	 * Revokes a permission from a group.
	 * @return Revocation success
	 */
	@Override public boolean revoke() {
		return false;
	}

	/**
	 * Checks whether a group has a given permission.
	 * @return Whether the group has the permission
	 */
	@Override public boolean hasPermission() {
		return false;
	}

	/**
	 * Retrieves a string from a group's data.
	 * @param path The location of the desired value.
	 * @return The string at the given path.
	 */
	public String getString(String path) throws IOException {
		return yamlService.getString(yamlService.groupsFile, name + "." + path);
	}
}
