package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientRegistryController {

  @GetMapping("/patientRegistry")
  public String patientRegistryForm(Model model) {
    model.addAttribute("patientRegistry", new PatientRegistry());
    return "receptionist";
  }

  // This is the one our submission is going to, better for more data and security
  @PostMapping("/patientRegistry")
  public String patientRegistrySubmit(@ModelAttribute PatientRegistry patientRegistry, Model model) {
    model.addAttribute("patientRegistry", patientRegistry);
    return "patientReg"; // this is what determines what page the submit button points to. Then must do a 
    // @RequestMapping in the Main.java 
  }

  /*@ModelAttribute("patientRegistry")
  public PatientRegistry loadEmptyModelBean() {
    return new PatientRegistry();
  }*/

}