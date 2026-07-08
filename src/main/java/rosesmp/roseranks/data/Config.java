package rosesmp.roseranks.data;

import rosesmp.roseranks.RoseRanks;
import rosesmp.roseranks.services.YAMLService;
import java.io.IOException;
import java.util.Map;

public class Config {
	private final YAMLService yamlService;

	public Config() {
		this.yamlService = RoseRanks.yamlService;
	}

	public Map<String, Object> load() throws IOException {
		return yamlService.load(yamlService.configFile);
	}
}
