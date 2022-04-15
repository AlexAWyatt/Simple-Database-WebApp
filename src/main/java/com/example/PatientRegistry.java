package com.example;

public class PatientRegistry {

    private String firstname;
    private String middlename;
    private String email_address;
    private String gender;
    private String insurance;
    private boolean is_employee;
    private boolean is_fifteen;
    private String responsible_party_ID;
    private String user_ID;
    private String patient_ID;

    // taken from user_profile


    private String city;
    private String date_of_birth;
    private String first_name;
    private String house_number;
    private String last_name;
    private String middle_name;
    private String password;
    private String province;
    private String ssn;
    private String street;

    public String getPatient_ID() {
        return patient_ID;
    }

    public void setPatient_ID(String patient_ID) {
        this.patient_ID = patient_ID;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public boolean getIs_employee() {
        return is_employee;
    }

    public void setIs_employee(boolean is_employee) {
        this.is_employee = is_employee;
    }

    public boolean getIs_fifteen() {
        return is_fifteen;
    }

    public void setIs_fifteen(boolean is_fifteen) {
        this.is_fifteen = is_fifteen;
    }

    public String getResponsible_party_ID() {
        return responsible_party_ID;
    }

    public void setResponsible_party_ID(String responsible_party_ID) {
        this.responsible_party_ID = responsible_party_ID;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_id(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean checkIfFifteen() {
        if (2022 - Integer.parseInt(date_of_birth.split("-")[0]) >= 15) {
            is_fifteen = true;
            return true;
        }
        return false;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }
}