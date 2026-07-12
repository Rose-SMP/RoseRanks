package rosesmp.roseranks.data;

import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static rosesmp.roseranks.RoseRanks.MOD_ID;
import static rosesmp.roseranks.RoseRanks.getGroups;

public class Config {
	private YamlConfiguration yaml;
	private File file;

	private String defaultGroup;

	public Config() {}

	public void load() throws IOException {
		this.file = new File(FabricLoader.getInstance().getConfigDir() + "/" + MOD_ID + "/config.yml");
		if (!file.exists()) {
			intialize();
		}

		this.yaml = new YamlConfiguration(file);
		yaml.load(this.getClass());

		//Populate fields of this object with the yml's data
		setDefaultGroup(yaml.getString("defaultGroup"));
	}

	private void intialize() throws IOException {
		file.getParentFile().mkdirs();
		file.createNewFile();

		try (InputStream inputStream = getClass().getResourceAsStream("/config.yml")) {
			if (inputStream == null) {
				throw new FileNotFoundException("Failed to get resource config.yml!");
			}
			Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
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

	public String getDefaultGroup() throws IOException {
		//Create group if it does not exist
		if (!getGroups().containsKey(defaultGroup)) {
			Group group = new Group();
			group.load(defaultGroup);
		}

		return defaultGroup;
	}

	public void setDefaultGroup(String defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
}
