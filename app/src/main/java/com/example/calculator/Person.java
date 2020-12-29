package com.example.calculator;

public class Person {
    private String email, password, phone, address;

    public Person(String email, String password, String phone, String address) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public Person() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
