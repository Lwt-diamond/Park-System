package entity;

public class ParkBill {
    private String billNo;
    private ParkCar parkCar;
    private String sourceNo;
    private String carStartDate;
    private String carEndDate;
    private float carStopHours;
    private float parkPrice;
    private float carFee;
    private String billAdminUserName;
    private String billAdminUserTel;

    public ParkBill(String billNo, ParkCar parkCar, String sourceNo, String carStartDate, String carEndDate, float carStopHours,float parkPrice, float carFee, String billAdminUserName, String billAdminUserTel) {
        this.billNo = billNo;
        this.parkCar = parkCar;
        this.sourceNo = sourceNo;
        this.carStartDate = carStartDate;
        this.carEndDate = carEndDate;
        this.carStopHours = carStopHours;
        this.parkPrice = parkPrice;
        this.carFee = carFee;
        this.billAdminUserName = billAdminUserName;
        this.billAdminUserTel = billAdminUserTel;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public ParkCar getParkCar() {
        return parkCar;
    }

    public void setParkCar(ParkCar parkCar) {
        this.parkCar = parkCar;
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

    public String getCarEndDate() {
        return carEndDate;
    }

    public void setCarEndDate(String carEndDate) {
        this.carEndDate = carEndDate;
    }

    public float getCarStopHours() {
        return carStopHours;
    }

    public void setCarStopHours(float carStopHours) {
        this.carStopHours = carStopHours;
    }

    public float getParkPrice() {
        return parkPrice;
    }

    public void setParkPrice(float parkPrice) {
        this.parkPrice = parkPrice;
    }

    public float getCarFee() {
        return carFee;
    }

    public void setCarFee(float carFee) {
        this.carFee = carFee;
    }

    public String getBillAdminUserName() {
        return billAdminUserName;
    }

    public void setBillAdminUserName(String billAdminUserName) {
        this.billAdminUserName = billAdminUserName;
    }

    public String getBillAdminUserTel() {
        return billAdminUserTel;
    }

    public void setBillAdminUserTel(String billAdminUserTel) {
        this.billAdminUserTel = billAdminUserTel;
    }

    @Override
    public String toString() {
        return "------ParkBill------ \n" +
                "billNo:   " + billNo + '\n' +
                "parkCar:  \n" + "        " +
                "carNo:  " + parkCar.getCarNo() +
                "\n        carType:  " + parkCar.getCarType() +
                "\n        carOwner:  " + parkCar.getCarOwner() +
                "\n        carTel:  " + parkCar.getCarTel() +
                "  " +
                "\nsourceNo: " + sourceNo + '\n' +
                "carStartDate:  " + carStartDate + '\n' +
                "carEndDate:  " + carEndDate + '\n' +
                "carStopHours:  " + carStopHours +
                "\ncarFee:  " + carFee +
                "\nbillAdminUserName:  " + billAdminUserName + '\n' +
                "billAdminUserTel=:  " + billAdminUserTel + '\n';
    }

}
