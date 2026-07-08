package rosesmp.roseranks.data;

import rosesmp.roseranks.RoseRanks;
import rosesmp.roseranks.services.APIService;
import rosesmp.roseranks.services.YAMLService;
import java.io.IOException;

public class PermissionEntity {
	public final YAMLService yamlService;
	public final APIService apiService;

	public PermissionEntity() {
		this.yamlService = RoseRanks.yamlService;
		this.apiService = RoseRanks.apiService;
	}

	//Load data for entity from file
	public boolean load() {
		return false;
	}

	//Save changes made to entity to file
	public boolean save() throws IOException {
		return false;
	}

	//Deletes entity's data
	public boolean delete() {
		return false;
	}

	//Grants a permission to the entity
	public boolean grant() {
		return false;
	}

	//Revokes a permission from the entity
	public boolean revoke() {
		return false;
	}

	//Checks whether the entity has a permission
	public boolean hasPermission() {
		return false;
	}

	public String getPrefix() {
		return "1";
	}
}
