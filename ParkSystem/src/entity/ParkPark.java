package entity;

public class ParkPark {
    public String carNo;
    public String sourceNo;
    public String carStartDate;
    public ParkPark(String carNo, String sourceNo, String carStartDate) {
        super();
        this.carNo = carNo;
        this.sourceNo = sourceNo;
        this.carStartDate = carStartDate;
    }
    public String getCarNo() {
        return carNo;
    }
    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
    public String getSourceNo() {
        return sourceNo;
    }
    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }
    public String getCarStartDate() {
        return carStartDate;
    }
    public void setCarStartDate(String carStartDate) {
        this.carStartDate = carStartDate;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParkPark other = (ParkPark) obj;
        if (carNo == null) {
            if (other.carNo != null)
                return false;
        } else if (!carNo.equals(other.carNo))
            return false;
        if (carStartDate == null) {
            if (other.carStartDate != null)
                return false;
        } else if (!carStartDate.equals(other.carStartDate))
            return false;
        if (sourceNo == null) {
            if (other.sourceNo != null)
                return false;
        } else if (!sourceNo.equals(other.sourceNo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ParkPark{" +
                "carNo='" + carNo + '\'' +
                ", sourceNo='" + sourceNo + '\'' +
                ", carStartDate='" + carStartDate + '\'' +
                '}';
    }
}
