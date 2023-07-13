package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;


import api.payloads.User;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response Create_user(User payload) {
		
		Response response = given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		.when()
			.post(Routes.post_user_url);
		
		return response;
	}
	
	public static Response Get_user(String userName) {
		
		Response response = given()
			.pathParam("username", userName)	
			.accept("application/json")
		.when()
			.get(Routes.get_user_url);
		
		return response;
	}
	
	public static Response Update_user(User user_payload,String userName) {
		
		Response response = given()
			.pathParam("username", userName)
			.contentType("application/json")
			.accept("application/json")
			.body(user_payload)
		.when()
			.put(Routes.update_user_url);
		
		return response;
	}
	
	public static Response Delete_user(String userName) {
		
		Response response = given()
			.pathParam("username", userName)	
			.accept("application/json")
		.when()
			.delete(Routes.delete_user_url);
		
		return response;
	}	
}
