package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int custId;

    @Column(length = 50, nullable = false, unique = true)
    private String fullName;

    @Column(length = 100)
    private String address;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String phoneNum;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String creditCardType;

    @Column(length = 30, unique = true)
    private String creditCardNum;

    @Column(length = 50, nullable = false)
    private String localDl;

    @Column(length = 50, nullable = false)
    private String ldlProvider;

    @Column(length = 50, nullable = false)
    private String internationalDl;

    @Column(length = 50, nullable = false)
    private String idlProvider;

    @Column(length = 20)
    private String zip;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(length = 50, nullable = false, unique = true)
    private String passportNum;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date passportValid;

    // Getters and Setters (no Lombok used)

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public String getLocalDl() {
        return localDl;
    }

    public void setLocalDl(String localDl) {
        this.localDl = localDl;
    }

    public String getLdlProvider() {
        return ldlProvider;
    }

    public void setLdlProvider(String ldlProvider) {
        this.ldlProvider = ldlProvider;
    }

    public String getInternationalDl() {
        return internationalDl;
    }

    public void setInternationalDl(String internationalDl) {
        this.internationalDl = internationalDl;
    }

    public String getIdlProvider() {
        return idlProvider;
    }

    public void setIdlProvider(String idlProvider) {
        this.idlProvider = idlProvider;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public Date getPassportValid() {
        return passportValid;
    }

    public void setPassportValid(Date passportValid) {
        this.passportValid = passportValid;
    }
}
