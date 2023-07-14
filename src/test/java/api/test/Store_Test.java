package api.test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payloads.Store;
import io.restassured.response.Response;

public class Store_Test {
	
	Store store_payload;
	Faker faker;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		store_payload = new Store();
		
		
		  store_payload.setId(faker.number().randomDigit());
		  store_payload.setPetId(faker.number().randomDigitNotZero());
		  store_payload.setQuantity(faker.number().numberBetween(0, 20));
//		  store_payload.setShipDate(faker.date().past(365 * 2, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(ZoneId.of("UTC"))); //placed, approved,delivered 
		
		  ZonedDateTime dateTime = faker.date().past(365 * 2, java.util.concurrent.TimeUnit.DAYS)
		      .toInstant().atZone(ZoneId.of("UTC"));
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		  String formattedDateTime = dateTime.format(formatter);

		  store_payload.setShipDate(formattedDateTime);
		  store_payload.setStatus("placed"); 
		  store_payload.setComplete(true);
		  
		  
		  logger = LogManager.getLogger(this.getClass());
		 
		
//		store_payload.setId(1001);
//		store_payload.setPetId(2);
//		store_payload.setQuantity(1);
//		store_payload.setShipDate("2023-07-13");
//		//placed, approved, delivered
//		store_payload.setStatus("placed");
//		store_payload.setComplete(true);
	}
	
	
	@Test(priority=1)
	public void TestPostOrder() {
		
		logger.info("***************** Place a Order ******************");	
		Response response = StoreEndPoints.Place_order(store_payload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***************** Order is placed ******************");	
	}
	
	@Test(priority=2)
	public void TestGetOrder() {
		logger.info("***************** Get Order Details  ******************");	
		Response response = StoreEndPoints.Read_order(store_payload.getId());
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***************** Order Details is Received ******************");	
	}
	
	@Test(priority=3)
	public void TestDeleteOrder() {
		logger.info("***************** Delete an Order ******************");	
		Response response = StoreEndPoints.Delete_order(store_payload.getId());
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***************** Order is deleted ******************");	
	}
	
	@Test(priority=4)
	public void TestGetInventory() {
		logger.info("***************** Get Inventory Details ******************");	
		Response response = StoreEndPoints.Read_inventory();
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***************** Inventory Details is Received ******************");	
	}
}
