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

	public String getUsername(UUID uuid) throws IOException {
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

	//Convert response into a usable JSON Object
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
