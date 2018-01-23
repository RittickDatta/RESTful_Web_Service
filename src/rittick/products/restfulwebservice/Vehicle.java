package rittick.products.restfulwebservice;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 

/***
 * 
 * @author Rittick
 * This is the POJO class for a vehicle.
 */
@XmlRootElement(name = "vehicle") 
public class Vehicle implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Vehicle has 4 properties.
	 */
	private int id;
	private int year;
	private String make;
	private String model;
	
	//Default Constructor
	public Vehicle(){}
	
	//Parameterized Constructor
	public Vehicle(int id, int year, String make, String model) {
		this.id = id;
		this.year = year;
		this.make = make;
		this.model = model;
	}
	
	/**
	 * This method is used to retrieve ID of the vehicle object.
	 * @return id (an integer)
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This method is used to set ID property of the vehicle object.
	 * @param id (an integer)
	 */
	@XmlElement 
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This method is used to retrieve Year of the vehicle object.
	 * @return int (an integer)
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * This method is used to set Year property of the vehicle object.
	 * @param year (an integer)
	 */
	@XmlElement
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * This method is used to retrieve Make of the vehicle object.
	 * @return String 
	 */
	public String getMake() {
		return make;
	}
	
	/**
	 * This method is used to set Make property of the vehicle object.
	 * @param make (a String)
	 */
	@XmlElement
	public void setMake(String make) {
		this.make = make;
	}
	
	/**
	 * This method is used to retrieve Model of the vehicle object.
	 * @return String
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * This method is used to set Model property of the vehicle object.
	 * @param model (a String)
	 */
	@XmlElement
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * This method compares two vehicle objects based on property values.
	 */
	@Override
	public boolean equals(Object object){
		if(object == null){
			return false;
		}else if(!(object instanceof Vehicle)){
			return false;
		}else {
			Vehicle vehicle = (Vehicle)object;
			if(id == vehicle.getId()
				&& year == vehicle.getYear()
				&& make.equals(vehicle.getMake())
				&& model.equals(vehicle.getModel())
					){
				return true;
			}
		}
		return false;
	}
}
