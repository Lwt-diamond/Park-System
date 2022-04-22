package entity;

public class ParkPrice {
    public static int freeHour= 1;
    private String carType;
    private  float parkPrice;

    public ParkPrice( String carType, float parkPrice) {
        this.carType = carType;
        this.parkPrice = parkPrice;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public float getParkPrice() {
        return parkPrice;
    }

    public void setParkPrice(float parkPrice) {
        this.parkPrice = parkPrice;
    }

    @Override
    public String toString() {
        return "ParkPrice{" +
                "carType='" + carType + '\'' +
                ", parkPrice=" + parkPrice +
                '}';
    }
}
