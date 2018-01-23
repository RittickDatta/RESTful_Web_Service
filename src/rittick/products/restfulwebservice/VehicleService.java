package rittick.products.restfulwebservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
@Path("/VehicleService")
/***
 * This is the Resource class. It contains methods with annotations to handle GET, GET with Id, POST, PUT, and DELETE 
 * with Id, requests. There are two constants: SUCCESS and FAILURE. Each method returns either of the two.
 * @author Rittick
 *
 */
public class VehicleService {
	//A VehicleDao object is used to access all its methods implementing GET, GET with id, POST, PUT, and DELETE with id.
	VehicleDao vehicleData = new VehicleDao();
	private static final String SUCCESS="<result>success</result>";
	private static final String FAILURE="<result>failure</result>";
	
	/**
	 * This method is called to get a list of all Vehicle objects.
	 * @return List<Vehicle>
	 */
	@GET
	@Path("/vehicles")
	@Produces(MediaType.APPLICATION_XML)
	public List<Vehicle> getVehicles(){
		return vehicleData.getAllVehicles();
	}
	
	/**
	 * This method is called to get a particular Vehicle identified by Id.
	 * @param vehicleid
	 * @return Response (a Vehicle object)
	 */
	@GET
	@Path("/vehicles/{vehicleid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getVehicle(@PathParam("vehicleid") int vehicleid){
		Vehicle vehicle = vehicleData.getVehicle(vehicleid);
		 return Response.ok()
				 		.entity(vehicle)
				 		.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").build();
	}
	
	/**
	 * This method is called to insert a new Vehicle object. All 4 parameter values are passed as parameters.
	 * @param id
	 * @param year
	 * @param make
	 * @param model
	 * @param servletResponse
	 * @return String (SUCCESS or FAILURE)
	 * @throws IOException
	 */
	@POST
	@Path("/vehicles")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createVehicle(@FormParam("Id") int id,
			@FormParam("Year") int year,
			@FormParam("Make") String make,
			@FormParam("Model") String model,
			@Context HttpServletResponse servletResponse) throws IOException{
		Vehicle vehicle = new Vehicle(id, year, make, model);
		int result = vehicleData.addVehicle(vehicle);
		if(result == 1){
			return SUCCESS;
		}
		return FAILURE;
	}
	
	/**
	 * This method is called to update an existing Vehicle object in file. Updated property values are passed as parameters.
	 * @param id
	 * @param year
	 * @param make
	 * @param model
	 * @param servletResponse
	 * @return String (SUCCESS or FAILURE)
	 * @throws IOException
	 */
	@PUT
	@Path("/vehicles")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateVehicle(@FormParam("Id") int id,
			@FormParam("Year") int year,
			@FormParam("Make") String make,
			@FormParam("Model") String model,
			@Context HttpServletResponse servletResponse) throws IOException{
		Vehicle vehicle = new Vehicle(id, year, make, model);
		int result = vehicleData.updateVehicle(vehicle);
		if(result == 1){
			return SUCCESS;
		}
		return FAILURE;
	}
	
	/**
	 * This method is called to delete a Vehicle object identified by Id.
	 * @param vehicleid
	 * @return String (SUCCESS or FAILURE)
	 */
	@DELETE
	@Path("/vehicles/{vehicleid}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteVehicle(@PathParam("vehicleid") int vehicleid){
		int result = vehicleData.deleteVehicle(vehicleid);
		if(result == 1){
			return SUCCESS;
		}
		return FAILURE;
	}
	
	/**
	 * This method returns the supported operations.
	 * @return String
	 */
	@OPTIONS
	@Path("/vehicles")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations(){
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
	
}
