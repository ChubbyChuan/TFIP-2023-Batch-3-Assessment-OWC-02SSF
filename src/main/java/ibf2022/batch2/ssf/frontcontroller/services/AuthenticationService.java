package ibf2022.batch2.ssf.frontcontroller.services;

import java.io.StringReader;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class AuthenticationService {

	
	public static final String RESTURL = "https://auth.chuklee.com";

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public String authenticate(String username, String password) throws Exception {
		
		JsonObject json = Json.createObjectBuilder()
							.add("username", username)
							.add("password", password)
							.build();


		RequestEntity<String> req = RequestEntity
		.post(RESTURL)
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(json.toString(), String.class);
		
		ResponseEntity<String> resp;
		RestTemplate template = new RestTemplate();
		try {
			resp = template.exchange(req, String.class);
		} catch (Exception ex) {
			throw ex;
		}

		String payload = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject js = reader.readObject();

		String message = js.getString("message");
		return message;

	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public boolean disableUser(String username) {

		LocalTime start = LocalTime.now();
		LocalTime futureTime = start.plusMinutes(30);

		if (futureTime.until(start, ChronoUnit.HOURS) > -0.5){
			return true;
		}
		
		return false;
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
