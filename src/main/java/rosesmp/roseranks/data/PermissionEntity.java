package rosesmp.roseranks.data;

public class PermissionEntity {

	public PermissionEntity() {

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

	//Gets the entity's current prefix
	public String getPrefix() {
		return "1";
	}
}
