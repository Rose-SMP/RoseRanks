package rosesmp.roseranks.data;

import net.fabricmc.loader.api.FabricLoader;
import java.io.File;

import static rosesmp.roseranks.RoseRanks.MOD_ID;

public class Config {
	private final YamlConfiguration yaml;
	private final File file;

	public Config() {
		this.file = new File(FabricLoader.getInstance().getConfigDir() + "/" + MOD_ID + "/config.yml");
		this.yaml = new YamlConfiguration(file);
	}

	/**
	 * @return The user's YAML.
	 */
	public YamlConfiguration getYaml() {
		return yaml;
	}

	/**
	 * @return config.yml.
	 */
	public File getFile() {
		return file;
	}
}
