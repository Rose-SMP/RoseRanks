package rosesmp.roseranks.data;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import java.io.File;
import java.io.IOException;
import org.yaml.snakeyaml.constructor.Constructor;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	public <T> void save(T object) throws IOException {
		Yaml yaml = new Yaml(new Constructor(object.getClass(), new LoaderOptions()));
		String yamlContent = yaml.dumpAs(object, Tag.MAP, null);
		Files.write(file.toPath(), yamlContent.getBytes());
	}

	/**
	 * Retrieves a property from the yml.
	 * @param path The location of the desired property.
	 * @return The desired property.
	 */
	private Object getProperty(String path) throws IOException {
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
		return getProperty(path).toString();
	}

	/**
	 * Retrieves an int from the yml.
	 * @param path The location of the desired int.
	 * @return The desired int.
	 */
	public int getInt(String path) throws IOException {
		return (int) getProperty(path);
	}
}
