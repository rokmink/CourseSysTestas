package com.example.lab1.dataStructures;

import javafx.fxml.FXML;

import java.io.Serializable;
import java.time.LocalDate;

public class Company extends User implements Serializable {
    private String companyName;
    private String representative;
    private String phoneNumber;

    public Company(String login, String password, String companyName, String representative, String phoneNumber) {
        super(login, password);
        this.companyName = companyName;
        this.representative = representative;
        this.phoneNumber = phoneNumber;
    }

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
