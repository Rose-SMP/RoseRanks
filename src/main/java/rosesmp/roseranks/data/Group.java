package rosesmp.roseranks.data;

import net.fabricmc.loader.api.FabricLoader;
import rosesmp.roseranks.RoseRanks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static rosesmp.roseranks.RoseRanks.MOD_ID;

/**
 * A group of players used for player categorization and permission allocation.
 */
public class Group {
	private YamlConfiguration yaml;
	private File file;
	private String name;

	private String prefix;
	private ArrayList<String> permissions;

	public Group() {}

	/**
	 * Loads a group's data from file.
	 */
	public void load(String name) throws IOException {
		this.name = name;
		this.permissions = new ArrayList<>();

		//Load yml
		file = new File(FabricLoader.getInstance().getConfigDir() + "/" + MOD_ID + "/groups/" + name + ".yml");
		if (!file.exists()) {
			intialize();
		}

		yaml = new YamlConfiguration(file);
		yaml.load(this.getClass());

		//Populate fields of this object with the yml's data
		setPrefix(yaml.getString("prefix"));
		setPermissions(yaml.getList("permissions"));

		RoseRanks.LOGGER.info("Loaded group {}!\nPrefix: {}.\nPermissions: {}", name, prefix, getPermissions());
	}

	/**
	 * Creates a group's file for creation.
	 */
	private void intialize() throws IOException {
		file.getParentFile().mkdirs();
		file.createNewFile();

		try (InputStream inputStream = getClass().getResourceAsStream("/group.yml")) {
			if (inputStream == null) {
				throw new FileNotFoundException("Failed to get resource group.yml!");
			}
			Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
	}

	/**
	 * Updates group's file with changes made during runtime.
	 */
	public void save() throws IOException {
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("permissions", permissions);

		yaml.save(data);
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

	/*
	 * Getters and Setters
	 */

	public YamlConfiguration getYaml() {
		return yaml;
	}

	public void setYaml(YamlConfiguration yaml) {
		this.yaml = yaml;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public ArrayList<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<String> permissions) {
		this.permissions = permissions;
	}
}
