package com.example;

public class AppointmentApps {

    private String appointment_id;
    private String appointment_type;
    private String date;
    private double total_fee_charge;
    public String patient_id;
    public String employee_id;
    private String start_time;
    private String end_time;
    private String status;
    private int assigned_room;

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getAppointment_type() {
        return appointment_type;
    }

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public int getAssigned_room() {
        return assigned_room;
    }

    public void setAssigned_room(int assigned_room) {
        this.assigned_room = assigned_room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal_fee_charge() {
        return total_fee_charge;
    }

    public void setTotal_fee_charge(double total_fee_charge) {
        this.total_fee_charge = total_fee_charge;
    }

}