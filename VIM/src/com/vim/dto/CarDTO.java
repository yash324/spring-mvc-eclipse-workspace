package com.vim.dto;
//todo's done
//Follow TODOs (if available)
/**
 * 
 * This is a CarDTO class
 * @see java.lang.Object
 * @author Abhishek
 * 
 *
 */
public class CarDTO
{
    private int id;
    private String make;
    private String model;
    private String modelYear;

    public CarDTO()
    {
    	this.id = -1;
    	this.make = "";
    	this.model = "";
    	this.modelYear= "";
        //TODO 1 initialize id to -1 and rest of the member variables to a blank string
		
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

}
