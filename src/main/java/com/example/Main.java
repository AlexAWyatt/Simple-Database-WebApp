package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/index")
  String index() {
    return "index"; // this function determines what page will be loaded into -- since "index" loads into index.html in src\main\resources\templates
  }

  @RequestMapping("/receptionist")
  String receptionist() {
    return "receptionist";
  }

  @RequestMapping("/dentisthyg")
  String dentisthyg() {
    return "dentisthyg";
  }

  @RequestMapping("/patient")
  String patient() {
    return "patient";
  }

  @RequestMapping("/patientRegistry")
  String patreged() {
    return "patientRegistry";
  }

  @RequestMapping("/branchui")
  String branchui() {
    return "branchui";
  }

  @GetMapping("/denttreats")
  public String denttreatsForm(Model model) {
    model.addAttribute("denttreats", new Denttreats());
    return "dentprocs";
  }


  @PostMapping("/denttreats")
  public String denttreatsSubmit(@ModelAttribute Denttreats denttreats, Model model, Map<String, Object> m) {
    model.addAttribute("denttreats", denttreats);
    String trtID = denttreats.getTreatment_ID();

    //System.out.println(patientApps.getPatient_ID());
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT treatment_type, tooth_involved, symptoms, comments, result, medication_rx_number FROM treatment WHERE treatment.treatment_id = '" + trtID + "';");

      if (isRSEmpty(rs)) {
        return "empty";
      } // If no results returned send to empty result page

     // ResultSet size = stmt.executeQuery("SELECT COUNT(*) FROM appointment WHERE appointment.patient_id = '" + patID + "';");
      //size.next();

      ArrayList<String> output = new ArrayList<String>();

      String result = null;
      
      while(rs.next()) {
        if (rs.getString(5).equals("t")) {
          result = "Success";
        }
        else {
          result = "Failed";
        }

        output.add("Treatment Type: " + rs.getString(1) + "      Tooth Involved: " + rs.getString(2) + "      Symptoms: " + rs.getString(3) + "      Comments: " + rs.getString(4)+ "      Result: " + result + "     medication_rx_number: " + rs.getString(6));
      }

      m.put("records10", output);

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "denttreats"; // MAKE this html
  }

  @GetMapping("/patientapproc")
  public String patientapprocForm(Model model) {
    model.addAttribute("patientapproc", new Patientapproc());
    return "dentappts";
  }


  @PostMapping("/patientapproc")
  public String patientapprocSubmit(@ModelAttribute Patientapproc patientapproc, Model model, Map<String, Object> m) {
    model.addAttribute("patientapproc", patientapproc);
    String apptID = patientapproc.getAppointment_ID();

    //System.out.println(patientApps.getPatient_ID());
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT procedure_type, description, treatment_id FROM appointment_procedure WHERE appointment_procedure.appointment_id = '" + apptID + "';");

      if (isRSEmpty(rs)) {
        return "empty";
      } // If no results returned send to empty result page

     // ResultSet size = stmt.executeQuery("SELECT COUNT(*) FROM appointment WHERE appointment.patient_id = '" + patID + "';");
      //size.next();

      ArrayList<String> output = new ArrayList<String>();
      
      while(rs.next()) {
        output.add("Procedure Type: " + rs.getString(1) + "      Description: " + rs.getString(2) + "      Treatment ID: " + rs.getString(3));
      }

      m.put("records9", output);

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "dentprocs";
  }


  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @GetMapping("/patientApps")
  public String patientAppsForm(Model model) {
    model.addAttribute("patientApps", new PatientApps());
    return "patient";
  }

  @PostMapping("/patientApps")
  public String patientAppsSubmit(@ModelAttribute PatientApps patientApps, Model model, Map<String, Object> m) {
    model.addAttribute("patientApps", patientApps);
    String patID = patientApps.getPatient_ID();

    //System.out.println(patientApps.getPatient_ID());
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM appointment WHERE appointment.patient_id = '" + patID + "';");
      //ResultSet rs = stmt.executeQuery("SELECT * FROM patient WHERE patient.patient_id = " + patID);

      if (isRSEmpty(rs)) {
        return "empty";
      } // If no results returned send to empty result page

     // ResultSet size = stmt.executeQuery("SELECT COUNT(*) FROM appointment WHERE appointment.patient_id = '" + patID + "';");
      //size.next();

      Statement stmt2 = connection.createStatement();

      ResultSet rs2 = stmt2.executeQuery("SELECT first_name, last_name FROM userprofile WHERE userprofile.user_id IN (SELECT user_id FROM employee WHERE employee.employee_id IN (SELECT employee_id FROM appointment WHERE appointment.patient_id = '" + patID + "'));");

      ArrayList<String> output = new ArrayList<String>();
      
      while(rs2.next()) { //rs.next()) {
        rs.next();
        output.add("*Date: " + rs.getString(3)  + "     *Start Time: " + rs.getString(7)  + "      *End Time: " + rs.getString(8)  +"\n *Dentist: "+ rs2.getString(1) + "      " + rs2.getString(2) + "      *Appointment Type: " + rs.getString(2)+ "      Status: " + rs.getString(9)+ "      Room: " + rs.getString(10));
      }

      m.put("records8", output);

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "upcomingappspat";
  }
  
  @GetMapping("/patientdent")
  public String patientdentForm(Model model) {
    model.addAttribute("patientdent", new Patientdent());
    return "dentisthyg";
  }

  @PostMapping("/patientdent")
  public String patientdentSubmit(@ModelAttribute Patientdent patientdent, Model model, Map<String, Object> m) {
    model.addAttribute("patientdent", patientdent);
    String empID = patientdent.getEmployee_ID();

    //System.out.println(patientApps.getPatient_ID());
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, user_id FROM userprofile WHERE userprofile.user_id IN (SELECT user_id FROM patient WHERE patient.patient_id IN (SELECT patient_id FROM appointment WHERE appointment.employee_id = '" + empID + "'));");

      if (isRSEmpty(rs)) {
        return "empty";
      } // If no results returned send to empty result page

     // ResultSet size = stmt.executeQuery("SELECT COUNT(*) FROM appointment WHERE appointment.patient_id = '" + patID + "';");
      //size.next();

      ArrayList<String> output = new ArrayList<String>();
      
      while(rs.next()) {
        output.add("Patient: " + rs.getString(1) + "      " + rs.getString(2) + "      User ID: " + rs.getString(3));
      }

      m.put("records3", output);

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "dentpats";
  }

  @GetMapping("/patientprog")
  public String patientprogForm(Model model) {
    model.addAttribute("patientprog", new Patientprog());
    return "dentpats";
  }

  @PostMapping("/patientprog")
  public String patientprogSubmit(@ModelAttribute Patientprog patientprog, Model model, Map<String, Object> m) {
    model.addAttribute("patientprog", patientprog);
    String usrID = patientprog.getUser_ID();

    //System.out.println(patientApps.getPatient_ID());
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT progress_notes FROM records_patient_charts WHERE records_patient_charts.patient_id IN (SELECT patient_id FROM patient WHERE patient.user_id = '" + usrID + "');");

      if (isRSEmpty(rs)) {
        return "empty";
      } // If no results returned send to empty result page

     // ResultSet size = stmt.executeQuery("SELECT COUNT(*) FROM appointment WHERE appointment.patient_id = '" + patID + "';");
      //size.next();

      ArrayList<String> output = new ArrayList<String>();
      
      while(rs.next()) {
        output.add("Progress Report: " + rs.getString(1));
      }

      m.put("records4", output);

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "dentprogs";
  }

  @GetMapping("/patientappts")
  public String patientapptsForm(Model model) {
    model.addAttribute("patientappts", new Patientappts());
    return "dentpats";
  }

  @PostMapping("/patientappts")
  public String patientapptsSubmit(@ModelAttribute Patientappts patientappts, Model model, Map<String, Object> m) {
    model.addAttribute("patientappts", patientappts);
    String usrID = patientappts.getUser_ID();


    //System.out.println(patientApps.getPatient_ID());
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT appointment_type, date, status, appointment_id FROM appointment WHERE appointment.patient_id IN (SELECT patient_id FROM patient WHERE patient.user_id = '" + usrID + "');");

      if (isRSEmpty(rs)) {
        return "empty";
      } // If no results returned send to empty result page

     // ResultSet size = stmt.executeQuery("SELECT COUNT(*) FROM appointment WHERE appointment.patient_id = '" + patID + "';");
      //size.next();

      ArrayList<String> output = new ArrayList<String>();
      
      while(rs.next()) {
        output.add("Appointment Type: " + rs.getString(1) + "      Date: " + rs.getString(2) + "      Status: " + rs.getString(3) + "      Appointment ID: " + rs.getString(4));
      }

      m.put("records5", output);

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "dentappts";
  }


  @GetMapping("/patientRegistry")
  public String patientRegistryForm(Model model) {
    model.addAttribute("patientRegistry", new PatientRegistry());
    return "receptionist";
  }

  @PostMapping("/patientRegistry")
  public String patientRegistrySubmit(@ModelAttribute PatientRegistry patientRegistry, Model model, Map<String, Object> m) {
    model.addAttribute("patientRegistry", patientRegistry);
    //String userID = patientRegistry.getUser_ID();

    //System.out.println(patientRegistry.getPatient_ID());
    //System.out.println(userID);
    //System.out.println(patientRegistry.getGender());

    try (Connection connection = dataSource.getConnection()) {

      //DELETE BAD ENTRIES
      //Statement stmt2 = connection.createStatement();
      //stmt2.executeUpdate("DELETE FROM patient WHERE email_address = 'null';");
      //stmt2.executeUpdate("DELETE FROM patient WHERE email_address = '12345694';");
      //stmt2.executeUpdate("DELETE FROM userprofile WHERE user_id = 'null';");
      //stmt2.executeUpdate("DELETE FROM userprofile WHERE user_id = '002314111';");

      Statement stmt = connection.createStatement();

      String patID = patientRegistry.getPatient_ID();
      String userID = patientRegistry.getUser_ID();
      String gender = patientRegistry.getGender();
      String insurance = patientRegistry.getInsurance();
      String email_address = patientRegistry.getEmail_address();
      boolean is_employee = false;
      boolean is_fifteen = patientRegistry.getIs_fifteen();

      //user profile stuff
      String first_name = patientRegistry.getFirst_name();
      String last_name = patientRegistry.getLast_name();
      String middle_name = patientRegistry.getMiddle_name();
      String city = patientRegistry.getCity();
      String province = patientRegistry.getProvince();;
      String street =  patientRegistry.getStreet();
      String house_number = patientRegistry.getHouse_number();
      String ssn = patientRegistry.getSsn();
      String date_of_birth = patientRegistry.getDate_of_birth();
      String password = patientRegistry.getPassword();
      String responsible_party_ID = patientRegistry.getResponsible_party_ID(); // IF TIME CHECK IF USER ID EXISTS

      //ResultSet rs = stmt.executeQuery("SELECT * FROM appointment WHERE patient_ID = " + patID);
      int x = stmt.executeUpdate("INSERT INTO userprofile VALUES('" + userID + "','" + first_name+ "','"  + middle_name+ "','"  + last_name+ "','"  + date_of_birth+ "','"  + house_number+ "','"  + street+ "','"  + city+ "','"  + province+ "','"  + ssn+ "','"  + password+ "','"  + responsible_party_ID + "') ON CONFLICT DO NOTHING;");
      int rs = stmt.executeUpdate("INSERT INTO patient VALUES('" + patID +"','"  + userID + "','"  + gender+ "','"  +insurance+ "','"  +email_address+ "','"  +is_employee+ "','"  +is_fifteen+ "','"  +responsible_party_ID + "') ON CONFLICT DO NOTHING;"); // WHERE patient_ID = " + patID);

      if (x == 0) {
        throw new Exception("Conflict in Users, no changes made to user.");
      }

      if (rs == 0) {
        throw new Exception("Conflict in Patient, no changes made.");
      }
      
      //Write query to check if addition is an employee and if os set is_employee as true before adding
      // SET AS FALSE FOR NOW


    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "receptionist";
  }

  @GetMapping("/employeeApps")
  public String employeeAppsForm(Model model) {
    model.addAttribute("employeeApps", new EmployeeApps());
    return "receptionist";
  }

  @PostMapping("/employeeApps")
  public String employeeAppsSubmit(@ModelAttribute EmployeeApps employeeApps, Model model, Map<String, Object> m) {
    model.addAttribute("employeeApps", employeeApps);

    

    //String userID = employeeApps.getUser_ID();

    //System.out.println(employeeApps.getEmployee_ID());
    //System.out.println(employeeApps.getSalary());
    //System.out.println(userID);
    
    try (Connection connection = dataSource.getConnection()) {

      Statement stmt = connection.createStatement();

      String employee_ID = employeeApps.getEmployee_ID();

      String user_ID = employeeApps.getUser_ID();
      String manager_ID = employeeApps.getManager_ID();

      String role = employeeApps.getRole();
      String employee_type = employeeApps.getEmployee_type();
      double salary = employeeApps.getSalary();

      String email_work = employeeApps.getEmail_work();
      String email_personal = employeeApps.getEmail_personal();

      String phone_extension = employeeApps.getPhone_extension();

      boolean furloughed = employeeApps.getFurloughed();

      // taken from user_profile
      String first_name = employeeApps.getFirst_name();
      String last_name = employeeApps.getLast_name();
      String middle_name = employeeApps.getMiddle_name();
      String city = employeeApps.getCity();
      String province = employeeApps.getProvince();;
      String street =  employeeApps.getStreet();
      String house_number = employeeApps.getHouse_number();
      String ssn = employeeApps.getSsn();
      String date_of_birth = employeeApps.getDate_of_birth();
      String password = employeeApps.getPassword();
      String responsible_party_ID = null;

      int x = stmt.executeUpdate("INSERT INTO userprofile VALUES('" + user_ID + "','"  + first_name+ "','"  + middle_name+ "','"  + last_name+ "','"  + date_of_birth+ "','"  + house_number+ "','"  + street+ "','"  + city+ "','"  + province+ "','"  + ssn+ "','"  + password+ "','"  + responsible_party_ID + "') ON CONFLICT DO NOTHING;");
      int rs = stmt.executeUpdate("INSERT INTO employee VALUES('" + employee_ID + "','"  + user_ID+ "','"  +manager_ID+ "','"  + role+ "','"  +employee_type+ "','"  +salary+ "','"  +email_work+ "','"  +email_personal+ "','"  +phone_extension+ "','"  +furloughed+ "') ON CONFLICT DO NOTHING;"); 
      
      if (x == 0) {
        throw new Exception("Conflict in Users, no changes made to user.");
      }

      if (rs == 0) {
        throw new Exception("Conflict in Employee, no changes made.");
      }

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "receptionist";
  }

  @GetMapping("/appointmentApps")
  public String appointmentAppsForm(Model model) {
    model.addAttribute("appointmentApps", new AppointmentApps());
    return "receptionist";
  }

  @PostMapping("/appointmentApps")
  public String appointmentAppsSubmit(@ModelAttribute AppointmentApps appointmentApps, Model model, Map<String, Object> m) {
    model.addAttribute("appointmentApps", appointmentApps);

    //String userID = employeeApps.getUser_ID();

    //System.out.println(employeeApps.getEmployee_ID());
    //System.out.println(userID);
    
    try (Connection connection = dataSource.getConnection()) {

      Statement stmt = connection.createStatement();

      String appointment_id = appointmentApps.getAppointment_id();
      String appointment_type = appointmentApps.getAppointment_type();
      String date = appointmentApps.getDate();
      double total_fee_charge = appointmentApps.getTotal_fee_charge();
      String patient_id = appointmentApps.getPatient_id();
      String employee_id = appointmentApps.getEmployee_id();
      String start_time = appointmentApps.getStart_time();
      String end_time = appointmentApps.getEnd_time();
      String status = appointmentApps.getStatus();
      int assigned_room = appointmentApps.getAssigned_room();

      int rs = stmt.executeUpdate("INSERT INTO appointment VALUES('" + appointment_id + "','"  + appointment_type+ "','"  +date+ "','"  + total_fee_charge+ "','"  +patient_id+ "','"  +employee_id+ "','"  +start_time+ "','"  +end_time+ "','"  +status+ "','"  +assigned_room +"') ON CONFLICT DO NOTHING;");

      if (rs == 0) {
        throw new Exception("Conflict in appointment, no appointment added.");
      }

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "receptionist";
  }

  @GetMapping("/branchEmploy")
  public String branchEmployForm(Model model) {
    model.addAttribute("branchEmploy", new BranchEmploy());
    return "branchui";
  }

  @PostMapping("/branchEmploy")
  public String branchEmploySubmit(@ModelAttribute BranchEmploy branchEmploy, Model model, Map<String, Object> m) {
    model.addAttribute("branchEmploy", branchEmploy);
    String brrID = branchEmploy.getBranch_ID();

    //System.out.println(branchEmploy.getBranch_ID());
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT first_name, last_name FROM userprofile WHERE userprofile.user_id IN (SELECT user_id FROM employee WHERE employee.role = 'Dentist' AND employee_id IN (SELECT employee_id FROM branch WHERE branch.branch_id = '" + brrID + "'));");

      if (isRSEmpty(rs)) {
        return "empty";
      }

      ArrayList<String> output = new ArrayList<String>();
      
      while(rs.next()) {
        output.add("Dentist: " + rs.getString(1) + "      " + rs.getString(2)) ;
      }

      m.put("records6", output);

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "branchdentist";
  }

  @RequestMapping("/approcs")
  public String approcsSubmit(Map<String, Object> m) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT procedure_type FROM appointment_procedure;");

      if (isRSEmpty(rs)) {
        return "empty";
      }

      ArrayList<String> output = new ArrayList<String>();

      while (rs.next()) {
        output.add("Procedure Type: " + rs.getString(1));
      }

      m.put("records7", output);
    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "proctype";
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }

  public static boolean isRSEmpty(ResultSet rs) throws SQLException {
    return (!rs.isBeforeFirst() && rs.getRow() ==0);
  }

}
