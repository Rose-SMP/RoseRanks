package rosesmp.roseranks.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class APIService {

	/**
	 * Fetches a username from the Mojang API by uuid.
	 * @param uuid The uuid used to query.
	 * @return The uuid's current associated username
	 * @throws IOException In the event of a bad http response
	 */
	public String fetchUsername(UUID uuid) throws IOException {
		//Establish connection and send request
		URL url = new URL("https://api.minecraftservices.com/minecraft/profile/lookup/" + uuid);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		//Validate response code
		int responseCode = connection.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK) {
			throw new IOException();
		}

		//Extract username from response and return
		return processResponse(connection).get("name").toString();
	}

	/**
	 * Converts an API response into a usable JsonObject
	 * @param connection The API request instance
	 * @return The converted JsonObject
	 * @throws IOException If the BufferedReader fails
	 */
	private JsonObject processResponse(HttpURLConnection connection) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String inputLine;
		StringBuilder response = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return JsonParser.parseString(response.toString()).getAsJsonObject();
	}
}
