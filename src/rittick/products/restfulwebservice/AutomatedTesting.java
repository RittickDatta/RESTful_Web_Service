package rittick.products.restfulwebservice;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class AutomatedTesting {
	
	private Client client;
	private String WEB_SERVICE_URL = "http://localhost:8080/Mitchell_CodingChallenge_RittickDatta/rest/VehicleService/vehicles";
	private static final String SUCCESS = "<result>success</result>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";
	
	private void init(){
		this.client = ClientBuilder.newClient();
	}
	
	public static void main(String[] args){
		AutomatedTesting automatedTesting = new AutomatedTesting();
		automatedTesting.init();
		automatedTesting.getAllVehicles();
		automatedTesting.getVehicle();
		automatedTesting.updateVehicle();
		automatedTesting.addVehicle();
		automatedTesting.deleteVehicle();
	}
	
	private void getAllVehicles(){
		GenericType<List<Vehicle>> list = new GenericType<List<Vehicle>>(){};
		List<Vehicle> vehicles = client
				.target(WEB_SERVICE_URL)
				.request(MediaType.APPLICATION_XML)
				.get(list);
		String result = PASS;
		if(vehicles.isEmpty()){
			result = FAIL;
		}
		System.out.println("Test Case - GET: getAllVehicles. Result: "+result);
	}
	
	private void getVehicle(){
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1);
		
		Vehicle newvehicle = client
				.target(WEB_SERVICE_URL)
				.path("/{vehicleid}")
				.resolveTemplate("vehicleid", 1)
				.request(MediaType.APPLICATION_XML)
				.get(Vehicle.class);
		String result = FAIL;
		if(vehicle != null && vehicle.getId() == newvehicle.getId()){
			result = PASS;
		}
		System.out.println("Test Case - GET by Id: getVehicle. Result: "+result);
	}
	
	private void updateVehicle(){
		Form form  = new Form();
		form.param("Id", "1");
		form.param("Year", "1995");
		form.param("Make", "Audi");
		form.param("Model", "B234");
		
		String callresult = client
				.target(WEB_SERVICE_URL)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(form,
						MediaType.APPLICATION_FORM_URLENCODED_TYPE),
						String.class);
		String result = PASS;
		if(!SUCCESS.equals(callresult)){
			result = FAIL;
		}
		System.out.println("Test Case - PUT: updateVehicle. Result: "+result);
	}
	
	private void addVehicle(){
		Form form = new Form();
		form.param("Id", "10");
		form.param("Year", "2000");
		form.param("Make", "BMW");
		form.param("Model", "C434");
		
		String callresult = client
				.target(WEB_SERVICE_URL)
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(form,
						MediaType.APPLICATION_FORM_URLENCODED_TYPE),
						String.class);
		
		String result = PASS;
		if(!SUCCESS.equals(callresult)){
			result = FAIL;
		}
		System.out.println("Test Case - POST: addVehicle. Result: "+result);
	}
	
	private void deleteVehicle(){
		String callresult = client
				.target(WEB_SERVICE_URL)
				.path("/{vehicleid}")
				.resolveTemplate("vehicleid", 10)
				.request(MediaType.APPLICATION_XML)
				.delete(String.class);
		
		String result = PASS;
		if(!SUCCESS.equals(callresult)){
			result = FAIL;
		}
		System.out.println("Test Case - DELETE: deleteVehicle. Result: "+result);
	}

}
