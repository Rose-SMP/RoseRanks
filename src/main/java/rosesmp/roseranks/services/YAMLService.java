package rosesmp.roseranks.services;

import net.fabricmc.loader.api.FabricLoader;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static rosesmp.roseranks.RoseRanks.MOD_ID;

public class YAMLService {
	public File configFile;
	public File usersFile;
	public File groupsFile;

	public YAMLService() {
		this.configFile = new File(FabricLoader.getInstance().getConfigDir() + "/" + MOD_ID + "/config.yml");
		this.usersFile = new File (FabricLoader.getInstance().getConfigDir() + "/" + MOD_ID + "/users.yml");
		this.groupsFile = new File (FabricLoader.getInstance().getConfigDir() + "/" + MOD_ID + "/groups.yml");
	}

	/**
	 * Saves data to a .yml file with nice formatting.
	 * @param file The file to which the data will be saved.
	 * @param data The data to be saved to the file.
	 * @return Save success
	 */
	public boolean save(File file, Map<String, Object> data) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);

		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setIndent(2);

		Yaml yaml = new Yaml(options);
		yaml.dump(data, writer);
		writer.close();

		return true;
	}

	/**
	 * Loads a file's data.
	 * @param file The file whose data will be loaded.
	 * @return Load success
	 */
	public boolean load(File file) throws IOException {
		if (!file.exists()) {
			return false;
		}
		InputStream inputStream = Files.newInputStream(Paths.get(file.getPath()));
		Yaml yaml = new Yaml();
		Map<String, Object> yml = yaml.load(inputStream);

		return true;
	}

	/**
	 * Retrieves a property from a file.
	 * @param file The file in which the data is stored.
	 * @param path The location of the desired property.
	 * @return The desired property
	 */
	private Object getProperty(File file, String path) throws IOException {
		InputStream inputStream = Files.newInputStream(Paths.get(file.getPath()));
		Yaml yaml = new Yaml();
		Map<String, Object> data = yaml.load(inputStream);

		return data.get(path);
	}

	/**
	 * Retrieves a string from a file.
	 * @param file The file in which the data is stored.
	 * @param path The location of the desired String.
	 * @return The desired String
	 */
	public String getString(File file, String path) throws IOException {
		return getProperty(file, path).toString();
	}

	/**
	 * Retrieves an integer from a file.
	 * @param file The file in which the data is stored.
	 * @param path The location of the desired integer.
	 * @return The desired integer
	 */
	public int getInt(File file, String path) throws IOException {
		return (int) getProperty(file, path);
	}
}
