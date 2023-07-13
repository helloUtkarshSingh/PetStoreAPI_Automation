package api.test;

import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class Data_driven_User_Test {
	
	@Test (priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void Test_Post_user(String UserID, String UserName, String FirstName, String LastName, String Email, String	Password, String Phone) {

		User user_payload = new User();
		Faker faker = new Faker();
		
		user_payload.setId(faker.number().hashCode());
		user_payload.setUsername(UserName);
		user_payload.setFirstName(FirstName);
		user_payload.setLastName(LastName);
		user_payload.setEmail(Email);
		user_payload.setPassword(Password);
		user_payload.setPhone(Phone);
		
		Response response = UserEndPoints.Create_user(user_payload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test (priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void Test_Delete_user(String UserName) {

		/*
		 * User user_payload = new User();
		 * 
		 * user_payload.getUsername();
		 */
		Response response = UserEndPoints.Delete_user(UserName);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
