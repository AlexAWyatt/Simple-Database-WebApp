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

import org.springframework.stereotype.Controller;
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

  // REMOVE THIS OR ADAPT
  @RequestMapping("/greeting")
  String greeting() {
    return "greeting";
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
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      //String patID = patientApps.getPatient_ID();
      //ResultSet rs = stmt.executeQuery("SELECT * FROM appointment WHERE patient_ID = " + patID);
      ResultSet rs = stmt.executeQuery("SELECT * FROM patient"); // WHERE patient_ID = " + patID);

      ArrayList<String> output = new ArrayList<String>();
      while(rs.next()) {
        output.add(rs.getString(3) + rs.getString("Insurance") + rs.getString("Email_address"));
      }

      m.put("records", output);

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "upcomingappspat";
  }
  
  @PostMapping("/procedureApps")
  public String patientAppsSubmit(@ModelAttribute ProcedureApps procedureApps, Model model, Map<String, Object> m) {
    model.addAttribute("procedureApps", procedureApps);
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      //String patID = patientApps.getPatient_ID();
      //ResultSet rs = stmt.executeQuery("SELECT * FROM appointment WHERE patient_ID = " + patID);
      ResultSet rs = stmt.executeQuery("SELECT procedure_type FROM appointment_procedure"); // WHERE patient_ID = " + patID);

      ArrayList<String> output = new ArrayList<String>();
      while(rs.next()) {
        output.add(rs.getString("procedure_type"));
      }

      m.put("records2", output);

    } catch (Exception e) {
      m.put("message", e.getMessage());
      return "error";
    }
    return "procedures_html";
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

}
