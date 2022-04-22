package entity;

public class ParkUser {
    private String userName;
    private String userPassword;
    private String userRight;


    public ParkUser(String userName, String userPassword, String userRight) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRight = userRight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRight() {
        return userRight;
    }

    public void setUserRight(String userRight) {
        this.userRight = userRight;
    }

    @Override
    public String toString() {
        return "ParkUser{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRight='" + userRight + '\'' +
                '}';
    }
}
