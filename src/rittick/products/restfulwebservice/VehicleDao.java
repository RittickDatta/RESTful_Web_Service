package rittick.products.restfulwebservice;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

/***
 * This class contains implemented methods for the 5 routes(GET, GET by Id, POST, PUT, and DELETE)
 * Also, it contains a method to persist data.
 * @author Rittick
 *
 */
public class VehicleDao {
	
	/***
	 * This method is called to retrieve a list of vehicle objects from file.
	 * @return List<Vehicle> (List of Vehicle objects)
	 */
	public List<Vehicle> getAllVehicles(){
		List<Vehicle> listOfVehicles = null;
		//System.out.print("In getAllVehicles");
		try {
			File file = new File("Vehicles.dat");
			//System.out.println(file.getAbsolutePath());
			if(!file.exists()){
				Vehicle vehicle = new Vehicle(1, 1990, "Honda", "A123");
				listOfVehicles = new ArrayList<Vehicle>();
				listOfVehicles.add(vehicle);
				listOfVehicles.add(new Vehicle(2, 2000, "Audi", "N129"));
				listOfVehicles.add(new Vehicle(3, 2010, "BMW", "B120"));
				saveVehicleList(listOfVehicles);
			}
			else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				listOfVehicles = (List<Vehicle>)ois.readObject();
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listOfVehicles;
	}
	
	/***
	 * This method is called to retrieve a particular Vehicle object identified by an "Id" property.
	 * @param id
	 * @return Vehicle (a Vehicle object)
	 */
	public Vehicle getVehicle(int id){
		List<Vehicle> allVehicles = getAllVehicles();
		
		for(Vehicle vehicle: allVehicles){
			if(vehicle.getId() == id){
				return vehicle;
			}
		}
		return null;
	}
	
	/***
	 * This method is called to add a Vehicle object.
	 * @param vehicle (a Vehicle object)
	 * @return 1 or 0 (1 to indicate success and 0 to indicate failure)
	 */
	public int addVehicle(Vehicle vehicle){
		List<Vehicle> allVehicles = getAllVehicles();
		boolean vehicleExists = false;
		for(Vehicle obj: allVehicles){
			if(obj.getId() == vehicle.getId()){
				vehicleExists = true;
				break;
			}
		}
		if(!vehicleExists){
			if (
					(vehicle.getMake() != null && !vehicle.getMake().isEmpty()) &&
					(vehicle.getModel() != null && !vehicle.getModel().isEmpty()) &&
					(vehicle.getYear() > 1950 && vehicle.getYear() < 2050)
					
				) 
			{
				allVehicles.add(vehicle);
				saveVehicleList(allVehicles);
				return 1;
			}
		}
		return 0;
	}
	
	/***
	 * This method is called to update a Vehicle object in file.
	 * @param vehicle (a Vehicle object with updated values)
	 * @return 1 or 0 (1 to indicate success and 0 to indicate failure)
	 */
	public int updateVehicle(Vehicle vehicle){
		List<Vehicle> allVehicles = getAllVehicles();
		
		for(Vehicle obj: allVehicles){
			if(obj.getId() == vehicle.getId()){
				int index = allVehicles.indexOf(obj);
				allVehicles.set(index, vehicle);
				saveVehicleList(allVehicles);
				return 1;
			}
		}
		return 0;
	}
	
	/***
	 * This method is called to delete a Vehicle object identified by "Id" property.
	 * @param id
	 * @return 1 or 0 (1 to indicate success and 0 to indicate failure)
	 */
	public int deleteVehicle(int id){
		List<Vehicle> allVehicles = getAllVehicles();
		
		for(Vehicle obj: allVehicles){
			if(obj.getId() == id){
				int index = allVehicles.indexOf(obj);
				allVehicles.remove(index);
				saveVehicleList(allVehicles);
				return 1;
			}
		}
		return 0;
	}
	
	/***
	 * This method is called to save the list of vehicles upon insertion, deletion, and upon updating a vehicle object.
	 * @param listOfVehicles (a list of Vehicle objects)
	 */
	private void saveVehicleList(List<Vehicle> listOfVehicles){
		try{
			System.out.print("In saveVehicleList");
			File file = new File("Vehicles.dat");
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listOfVehicles);
			oos.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
