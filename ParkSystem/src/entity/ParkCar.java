package entity;

public class ParkCar {
    private String carNo;
    private String carType;
    private String carOwner;
    private String carTel;
    public ParkCar(String carNo, String carType, String carOwner, String carTel) {
        super();
        this.carNo = carNo;
        this.carType = carType;
        this.carOwner = carOwner;
        this.carTel = carTel;
    }
    public String getCarNo() {
        return carNo;
    }
    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
    public String getCarType() {
        return carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getCarOwner() {
        return carOwner;
    }
    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }
    public String getCarTel() {
        return carTel;
    }
    public void setCarTel(String carTel) {
        this.carTel = carTel;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParkCar other = (ParkCar) obj;
        if (carNo == null) {
            if (other.carNo != null)
                return false;
        } else if (!carNo.equals(other.carNo))
            return false;
        if (carOwner == null) {
            if (other.carOwner != null)
                return false;
        } else if (!carOwner.equals(other.carOwner))
            return false;
        if (carTel == null) {
            if (other.carTel != null)
                return false;
        } else if (!carTel.equals(other.carTel))
            return false;
        if (carType == null) {
            if (other.carType != null)
                return false;
        } else if (!carType.equals(other.carType))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ParkCar{" +
                "carNo='" + carNo + '\'' +
                ", carType='" + carType + '\'' +
                ", carOwner='" + carOwner + '\'' +
                ", carTel='" + carTel + '\'' +
                '}';
    }
}
