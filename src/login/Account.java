package login;

import java.io.Serializable;

public class Account implements Serializable {
    static final long serialVersionUID = 1L;

    private int id;
    private String userName;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;

    public Account() {
    }

    public Account(int id, String userName, String password, String fullName, String phoneNumber, String address) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }



    public Account(int i, String s) {
    }



    public Account(String id, String userName, String password, String fullName, String phoneNumber, String address) {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-20s%-20s%-20s%-30s",
                "ID", "Tài khoản", "Mật khẩu", "Họ và tên", "Số điện thoại", "Địa chỉ nhận hàng") + "\n" +
                String.format("%-10s%-20s%-20s%-20s%-20s%-30s",
                        id, userName, password, fullName, phoneNumber, address);
    }
    }




