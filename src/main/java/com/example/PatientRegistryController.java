package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientRegistryController {

  @GetMapping("/patreg")
  public String patregForm(Model model) {
    model.addAttribute("patreg", new PatientRegistry());
    return "patreg";
  }

  @PostMapping("/patreg")
  public String patregSubmit(@ModelAttribute PatientRegistry patreg, Model model) {
    model.addAttribute("patreg", patreg);
    return "result";
  }

}