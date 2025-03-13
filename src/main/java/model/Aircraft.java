package model;

public class Aircraft {
    private String aircraftCode;
    private String model;
    private int capacity;

    public Aircraft(){}


    public Aircraft(String aircraftCode,String model,int capacity){
            this.aircraftCode=aircraftCode;
            this.model=model;
            this.capacity=capacity;
}
    // Getters and Setters
    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

