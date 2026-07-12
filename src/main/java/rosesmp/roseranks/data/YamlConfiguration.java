package rosesmp.roseranks.data;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class YamlConfiguration {
	private final File file;

	public YamlConfiguration(File file) {
		this.file = file;
	}

	/*
	* Many thanks to https://github.com/kgromov/snake-yaml-examples!
	*/

	/**
	 * Loads an object from a yml.
	 */
	public <T> T load(Class<T> clazz) {
		try (InputStream inputStream = Files.newInputStream(file.toPath())) {
			return new Yaml().loadAs(inputStream, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Writes an object to a yml file.
	 */
	public void save(Map<String, Object> data) throws IOException {
		PrintWriter writer = new PrintWriter(file);

		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setIndent(2);

		Yaml yaml = new Yaml(options);
		yaml.dump(data, writer);
		writer.close();
	}

	/**
	 * Retrieves a property from the yml.
	 * @param path The location of the desired property.
	 * @return The desired property.
	 */
	private Object get(String path) throws IOException {
		InputStream inputStream = Files.newInputStream(Paths.get(file.getPath()));
		Yaml yaml = new Yaml();
		Map<String, Object> data = yaml.load(inputStream);

		return data.get(path);
	}

	/**
	 * Retrieves a string from the yml.
	 * @param path The location of the desired String.
	 * @return The desired String
	 */
	public String getString(String path) throws IOException {
		return get(path).toString();
	}

	/**
	 * Retrieves an int from the yml.
	 * @param path The location of the desired int.
	 * @return The desired int.
	 */
	public int getInt(String path) throws IOException {
		return (int) get(path);
	}

	/**
	 * Retrieves a string from the yml.
	 * @param path The location of the desired String.
	 * @return The desired String
	 */
	public ArrayList<String> getList(String path) throws IOException {
		//TODO: Find a better way to do this
		return (ArrayList<String>) get(path);
	}

	public File getFile() {
		return file;
	}
}
