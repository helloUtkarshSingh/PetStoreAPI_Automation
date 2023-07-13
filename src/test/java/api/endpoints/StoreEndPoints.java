package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;



import api.payloads.Store;
import io.restassured.response.Response;

public class StoreEndPoints {
	
	public static Response Place_order(Store payload) {
		
		Response response = given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		
		.when()
			.post(Routes.post_order_url);
		
		return response;
		
	}	
	
	public static Response Read_order(int order_id) {
		
		Response response = given()
				.accept("application/json")
				.pathParam("order_id", order_id)
			
			.when()
				.get(Routes.get_order_url);
			
			return response;	
	}
	
   public static Response Delete_order(int order_id) {
		
		Response response = given()
				.accept("application/json")
				.pathParam("order_id", order_id)
			
			.when()
				.delete(Routes.delete_order_url);
			
			return response;	
	}
   
   public static Response Read_inventory() {
		
		Response response = given()
				.accept("application/json")
			
			.when()
				.get(Routes.get_inventory_url);
			
			return response;	
	}   

}
