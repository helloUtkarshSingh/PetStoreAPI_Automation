package api.payloads;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.payloads.Store;

public class sample {
	
	@Test()
	public void  sample2(){
	Faker faker = new Faker();
	Store store_payload = new Store();
	
	
	  store_payload.setId(faker.number().randomDigit());
	  store_payload.setPetId(faker.number().randomDigitNotZero());
	  store_payload.setQuantity(faker.number().numberBetween(0, 20));
//	  store_payload.setShipDate(faker.date().past(365 * 2, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(ZoneId.of("UTC"))); //placed, approved,delivered 
	
	  ZonedDateTime dateTime = faker.date().past(365 * 2, java.util.concurrent.TimeUnit.DAYS)
	      .toInstant().atZone(ZoneId.of("UTC"));
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	  String formattedDateTime = dateTime.format(formatter);

	  store_payload.setShipDate(formattedDateTime);
	  store_payload.setStatus("placed"); 
	  store_payload.setComplete(true);
	  
	  System.out.println("ID: "+store_payload.getId());
	  System.out.println("PetID: "+store_payload.getPetId());
	  System.out.println("Quantity: "+store_payload.getQuantity());
	  System.out.println("Date: "+store_payload.getShipDate());
	  System.out.println("Status: "+store_payload.getStatus());
	  System.out.println("Complete: "+store_payload.getComplete());
	}
}
