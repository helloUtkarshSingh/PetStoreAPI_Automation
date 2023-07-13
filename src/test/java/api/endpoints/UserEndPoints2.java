package api.endpoints;  //Using properties files to take endpoints

import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	static ResourceBundle getURL(){
		
		ResourceBundle routes = ResourceBundle.getBundle("Routes"); // to read properties files
		return routes;
	}
	
	
	public static Response Create_user(User payload) {
		
		String post_user_url = getURL().getString("post_url");
		
		Response response = given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		.when()
			.post(post_user_url);
		
		return response;
	}
	
	public static Response Get_user(String userName) {
		
		String get_user_url = getURL().getString("get_url");
		Response response = given()
			.pathParam("username", userName)	
			.accept("application/json")
		.when()
			.get(get_user_url);
		
		return response;
	}
	
	public static Response Update_user(User user_payload,String userName) {
		
		String update_user_url = getURL().getString("update_url");
		Response response = given()
			.pathParam("username", userName)
			.contentType("application/json")
			.accept("application/json")
			.body(user_payload)
		.when()
			.put(update_user_url);
		
		return response;
	}
	
	public static Response Delete_user(String userName) {
		
		String delete_user_url = getURL().getString("delete_url");
		Response response = given()
			.pathParam("username", userName)	
			.accept("application/json")
		.when()
			.delete(delete_user_url);
		
		return response;
	}	
}
