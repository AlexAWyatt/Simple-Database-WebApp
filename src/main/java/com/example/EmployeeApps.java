package com.example;

public class EmployeeApps {

    private static String employee_id;
    public static String user_id;
    public static String manager_id;
    private static String role;
    private static String employee_type;
    private static Double salary;
    private static String email_work;
    private static String email_personal;
    private static String phone_extension;
    private static boolean furloughed;

    // taken from user_profile
    private static String first_name;
    private static String last_name;
    private static String middle_name;
    private static String city;
    private static String province;
    private static String street;
    private static String house_number;
    private static String ssn;
    private static String date_of_birth;
    private static String password;
    private static String responsible_party_id;

    public static String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public static String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public static String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public static String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public static String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public static String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public static String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public static String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getResponsible_party_id() {
        return responsible_party_id;
    }

    public void setResponsible_party_id(String responsible_party_id) {
        this.responsible_party_id = responsible_party_id;
    }


    public static String getEmail_personal() {
        return email_personal;
    }

    public void setEmail_personal(String email_personal) {
        this.email_personal = email_personal;
    }

    public static String getEmail_work() {
        return email_work;
    }

    public void setEmail_work(String email_work) {
        this.email_work = email_work;
    }

    public static String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public static String getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }

    public static String getManager_id() {
        return manager_id;
    }
    
    

    public void setFurloughed(boolean furloughed) {
        this.furloughed = furloughed;
    }

    public static String getPhone_extension() {
        return phone_extension;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public static String getRole() {
        return role;
    }

    public void setPhone_extension(String phone_extension) {
        this.phone_extension = phone_extension;
    }

    public static Double getSalary() {
        return salary;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static String getUser_id() {
        return user_id;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public static Boolean getFurloughed() {
        return furloughed;
    }

    public void setFurloughed(Boolean furloughed) {
        this.furloughed = furloughed;
    }

}
