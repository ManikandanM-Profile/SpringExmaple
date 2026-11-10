package com.example.SpringExmaple.Controller;

import com.example.SpringExmaple.DTO.Employee;
import com.example.SpringExmaple.Repository.EmployeeRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

	@GetMapping("/profile")
	public String showProfile(Model model) {

		model.addAttribute("employees", employeeRepository.findAll());

		return "employee-list";
	}
    
    // Display employee form
    @GetMapping("/employees/new")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    // Save employee to database
    @PostMapping("/employees/save")
    public String saveEmployee(
            @ModelAttribute("employee") Employee employee) {

        employeeRepository.save(employee);

        return "redirect:/employees/new?success";
    }
}