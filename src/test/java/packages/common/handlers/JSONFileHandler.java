package packages.common.handlers;

import java.io.BufferedWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONFileHandler {
	public class JacksonJson {
		public void readJSON() {
			try {
				// create a reader
				Reader reader = Files.newBufferedReader(Paths.get("customer.json"));

				// create ObjectMapper instance
				ObjectMapper objectMapper = new ObjectMapper();

				// read customer.json file into tree model
				JsonNode parser = objectMapper.readTree(reader);

				// read customer details
				System.out.println(parser.path("id").asLong());
				System.out.println(parser.path("name").asText());
				System.out.println(parser.path("email").asText());
				System.out.println(parser.path("age").asLong());

				// read address
				JsonNode address = parser.path("address");
				System.out.println(address.path("street").asText());
				System.out.println(address.path("city").asText());
				System.out.println(address.path("state").asText());
				System.out.println(address.path("zipCode").asLong());

				// read payment method
				for (JsonNode pm : parser.path("paymentMethods")) {
					System.out.println(pm.asText());
				}

				// read projects
				for (JsonNode project : parser.path("projects")) {
					System.out.println(project.path("title").asText());
					System.out.println(project.path("budget").asLong());
				}

				// close reader
				reader.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void createJSON() {
			try {
				// create a writer
				BufferedWriter writer = Files.newBufferedWriter(Paths.get("customer.json"));

				// create a map for customer properties
				Map<String, Object> customer = new HashMap<>();
				customer.put("id", 1);
				customer.put("name", "John Doe");
				customer.put("email", "john.doe@example.com");
				customer.put("age", 32);

				// create address
				Map<String, Object> address = new HashMap<>();
				address.put("street", "155 Middleville Road");
				address.put("city", "New York");
				address.put("state", "New York");
				address.put("zipCode", 10045);

				// add address to customer
				customer.put("address", address);

				// create payment methods
				customer.put("paymentMethods", Arrays.asList("PayPal", "Stripe"));

				// create 1st project
				Map<String, Object> p1 = new HashMap<>();
				p1.put("title", "Business Website");
				p1.put("budget", 4500);

				// create 2nd project
				Map<String, Object> p2 = new HashMap<>();
				p2.put("title", "Sales Dashboard");
				p2.put("budget", 8500);

				// add projects to customer
				customer.put("projects", Arrays.asList(p1, p2));

				// create ObjectMapper instance
				ObjectMapper mapper = new ObjectMapper();

				// write JSON to file
				writer.write(mapper.writeValueAsString(customer));

				// close the writer
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public class GsonJson {
		public void readJSON() {
			try {
				// create a reader
				Reader reader = Files.newBufferedReader(Paths.get("customer.json"));

				// create JsonObject instance
				JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

				// read customer details
				System.out.println(parser.get("id").getAsLong());
				System.out.println(parser.get("name").getAsString());
				System.out.println(parser.get("email").getAsString());
				System.out.println(parser.get("age").getAsLong());

				// read address
				JsonObject address = parser.get("address").getAsJsonObject();
				System.out.println(address.get("street").getAsString());
				System.out.println(address.get("city").getAsString());
				System.out.println(address.get("state").getAsString());
				System.out.println(address.get("zipCode").getAsLong());

				// read payment method
				for (JsonElement pm : parser.get("paymentMethods").getAsJsonArray()) {
					System.out.println(pm.getAsString());
				}

				// read projects
				for (JsonElement project : parser.get("projects").getAsJsonArray()) {
					JsonObject obj = project.getAsJsonObject();
					System.out.println(obj.get("title").getAsString());
					System.out.println(obj.get("budget").getAsLong());
				}

				// close reader
				reader.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void createJSON() {
			try {
				// create a writer
				BufferedWriter writer = Files.newBufferedWriter(Paths.get("customer.json"));

				// create a map for customer properties
				Map<String, Object> customer = new HashMap<>();
				customer.put("id", 1);
				customer.put("name", "John Doe");
				customer.put("email", "john.doe@example.com");
				customer.put("age", 32);

				// create address
				Map<String, Object> address = new HashMap<>();
				address.put("street", "155 Middleville Road");
				address.put("city", "New York");
				address.put("state", "New York");
				address.put("zipCode", 10045);

				// add address to customer
				customer.put("address", address);

				// create payment methods
				customer.put("paymentMethods", Arrays.asList("PayPal", "Stripe"));

				// create 1st project
				Map<String, Object> p1 = new HashMap<>();
				p1.put("title", "Business Website");
				p1.put("budget", 4500);

				// create 2nd project
				Map<String, Object> p2 = new HashMap<>();
				p2.put("title", "Sales Dashboard");
				p2.put("budget", 8500);

				// add projects to customer
				customer.put("projects", Arrays.asList(p1, p2));

				// create Gson instance
				Gson gson = new Gson();

				// write JSON to file
				writer.write(gson.toJson(customer));

				// close the writer
				writer.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
