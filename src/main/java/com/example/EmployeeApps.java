package com.example;

public class EmployeeApps {

    private String employee_id;
    public String user_id;
    public String manager_id;
    private String role;
    private String employee_type;
    private int salary;
    private String email_work;
    private String email_personal;
    private String phone_extension;
    private boolean furloughed;

    // taken from user_profile
    private String first_name;
    private String last_name;
    private String middle_name;
    private String city;
    private String province;
    private String street;
    private String house_number;
    private String ssn;
    private String date_of_birth;
    private String password;
    private String responsible_party_id;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getResponsible_party_id() {
        return responsible_party_id;
    }

    public void setResponsible_party_id(String responsible_party_id) {
        this.responsible_party_id = responsible_party_id;
    }


    public String getEmail_personal() {
        return email_personal;
    }

    public void setEmail_personal(String email_personal) {
        this.email_personal = email_personal;
    }

    public String getEmail_work() {
        return email_work;
    }

    public void setEmail_work(String email_work) {
        this.email_work = email_work;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }

    public String getManager_id() {
        return manager_id;
    }
    
    

    public void setFurloughed(boolean furloughed) {
        this.furloughed = furloughed;
    }

    public String getPhone_extension() {
        return phone_extension;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public String getRole() {
        return role;
    }

    public void setPhone_extension(String phone_extension) {
        this.phone_extension = phone_extension;
    }

    public int getSalary() {
        return salary;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Boolean getFurloughed() {
        return furloughed;
    }

    public void setFurloughed(Boolean furloughed) {
        this.furloughed = furloughed;
    }

}
