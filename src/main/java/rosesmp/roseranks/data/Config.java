package rosesmp.roseranks.data;

import java.io.IOException;
import java.util.Map;

import static rosesmp.roseranks.RoseRanks.yamlService;

public class Config {

	public Map<String, Object> load() throws IOException {
		return yamlService.load(yamlService.configFile);
	}
}
