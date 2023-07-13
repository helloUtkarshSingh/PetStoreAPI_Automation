package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;

import api.payloads.User;
import io.restassured.response.Response;

public class User_Test {
	
	Faker faker;
	User user_payload;

	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
	    user_payload = new User();
	  
	
	    //Original PayLoad	
		user_payload.setId(faker.idNumber().hashCode());
		user_payload.setUsername(faker.name().username());
		user_payload.setFirstName(faker.name().firstName());
		user_payload.setLastName(faker.name().lastName());
		user_payload.setEmail(faker.internet().emailAddress());
		user_payload.setPassword(faker.internet().password());
		user_payload.setPhone(faker.phoneNumber().cellPhone());
		
		//Log 
		
		logger = LogManager.getLogger(this.getClass());
        

	}
	
	@Test(priority=1)
	public void TestPostUser() {
		
		logger.info("************ Creating User ************");
		Response response = UserEndPoints.Create_user(user_payload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User is Created ************");
		
	}
	
	@Test(priority=2)
	public void TestGetUser() {
		logger.info("************ Reading User ************");
		Response response = UserEndPoints.Get_user(user_payload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User got display ************");
		
	}

	@Test(priority=3)
	public void TestUpdateUser() {
		logger.info("************ Update User************");
		user_payload.setUsername(faker.name().username());
		user_payload.setFirstName(faker.name().firstName());
		user_payload.setLastName(faker.name().lastName());
		user_payload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndPoints.Update_user(user_payload,user_payload.getUsername());
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	
		logger.info("************ User got updated ************");
		logger.info("************ Displaying Updated User ************");
		Response responseAfterUpdate = UserEndPoints.Get_user(user_payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("************ Updated user got display ************");
		
	}

	
	
	@Test(priority=4)
	public void TestDeleteUser() {
		logger.info("************ Deleting User ************");
		
		Response response = UserEndPoints.Delete_user(user_payload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("************ User got Deleted ************");
	}
	

}
