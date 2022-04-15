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
