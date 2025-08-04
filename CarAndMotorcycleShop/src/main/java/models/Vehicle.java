package models;

public class Vehicle {
	private int vehicleId;
	private VehicleType type;
	private String brand, model;
	private VehicleStatus status;
	private int year;
	private double price;
	
	public Vehicle() {}

	public Vehicle(int vehicleId, VehicleType type, String brand, String model, VehicleStatus status, int year,
			double price) {
		super();
		this.vehicleId = vehicleId;
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.status = status;
		this.year = year;
		this.price = price;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
