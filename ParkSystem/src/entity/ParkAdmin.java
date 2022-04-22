package entity;

import java.io.File;

public class ParkAdmin {
    private String userName;
    private String adminName;
    private String adminID;
    private String adminBirthday;
    private String adminSex;
    private String adminAddress;
    private String adminTel;
    private String adminPhoto;

    public ParkAdmin(String userName, String adminName, String adminID, String adminBirthday, String adminSex, String adminAddress, String adminTel, String adminPhoto) {
        this.userName = userName;
        this.adminName = adminName;
        this.adminID = adminID;
        this.adminBirthday = adminBirthday;
        this.adminSex = adminSex;
        this.adminAddress = adminAddress;
        this.adminTel = adminTel;
        this.adminPhoto = adminPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminBirthday() {
        return adminBirthday;
    }

    public void setAdminBirthday(String adminBirthday) {
        this.adminBirthday = adminBirthday;
    }

    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel;
    }

    public String getAdminPhoto() {
        return adminPhoto;
    }

    public void setAdminPhoto(String adminPhoto) {
        this.adminPhoto = adminPhoto;
    }

    @Override
    public String toString() {
        return "ParkAdmin{" +
                "userName='" + userName + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminID='" + adminID + '\'' +
                ", adminBirthday='" + adminBirthday + '\'' +
                ", adminSex='" + adminSex + '\'' +
                ", adminAddress='" + adminAddress + '\'' +
                ", adminTel='" + adminTel + '\'' +
                ", adminPhoto=" + adminPhoto +
                '}';
    }
}

