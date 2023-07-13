package api.test;

import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payloads.Store;
import io.restassured.response.Response;

public class Store_Test {
	
	Store store_payload;
	Faker faker;
	
	@BeforeClass
	public void setupdata() {
		
		faker = new Faker();
		store_payload = new Store();
		
		store_payload.setId(faker.number().randomDigitNotZero());
		store_payload.setPetId(faker.number().randomDigitNotZero());
		store_payload.setQuantity(faker.number().numberBetween(0, 20));
		store_payload.setShipDate(faker.date().toString());
		//placed, approved, delivered
		store_payload.setStatus("placed");
		store_payload.setComplete(true);
	}
	
	@Test
	public void TestPostOrder() {
		
		Response response = StoreEndPoints.Place_order(store_payload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void TestGetOrder() {
		
		Response response = StoreEndPoints.Read_order(store_payload.getId());
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void TestDeleteOrder() {
		
		Response response = StoreEndPoints.Delete_order(store_payload.getId());
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void TestGetInventory() {
		
		Response response = StoreEndPoints.Read_inventory();
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
}
