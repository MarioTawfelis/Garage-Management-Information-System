/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

/**
 *
 * @author EfthymiosChatziathanasiadis
 */
public  class Vehicle {
    
    private String registrationNumber;
    private String model;
    private String manufacturer;
    private String engineSize;
    private String fuelType;
    private String colour;
    private String motDate;
    private String mileage;
    private String lastServiceDate;
    private String vehicleType;
    
    public Vehicle(String type, String registrationNumber, String manufacturer, String model, String engineSize, String fuelType, String colour, String motDate, String mileage, String lastServiceDate){
        this.vehicleType=type;
        this.registrationNumber=registrationNumber;
        this.manufacturer=manufacturer;
        this.model=model;
        this.engineSize=engineSize;
        this.fuelType=fuelType;
        this.colour=colour;
        this.motDate=motDate;
        this.mileage=mileage;
        this.lastServiceDate=lastServiceDate;
           
    }
    
    /***************************************************************************************
    * GETTERS AND SETTERS FOR VEHICLE INSTANCE VARIABLES
    *****************************************************************************************/
    
    
    
    public void setVehicleType(String type){
        this.vehicleType=type;
    }
    public String getVehicleType(){
        return this.vehicleType;
    }
    
    public void setRegnum(String regnum){
        this.registrationNumber=regnum;
    }
    public String getRegnum(){
        return this.registrationNumber;
    }
    
    public void setModel(String model){
        this.model=model;
    }
    public String getModel(){
        return this.model;
    }
    
    public String getMileage(){
        return this.mileage;
    }
    public void setMilage(String milage){
        this.mileage=milage;
    }
    
    public void setColour(String col){
        this.colour=col;
    }
    public String getCol(){
        return this.colour;
    }
    
    public void setEngSize(String eng){
        this.engineSize=eng;
    }
    public String getEngSize(){
        return this.engineSize;
    }
    
    public void setFuelType(String fuel){
        this.fuelType=fuel;
    }
    public String getFuel(){
        return this.fuelType;
    }
    
    public void setMoT(String mot){
        this.motDate=mot;
    }
    public String getMoT(){
        return this.motDate;
    }
    
    public String getLastService(){
        return this.lastServiceDate;
    }
    public void setLastService(String last){
        this.lastServiceDate=last;
    }
    public void setMake(String make){
        this.manufacturer=make;
    }
    public String getMake(){
        return this.manufacturer;
    }
    
    
}
