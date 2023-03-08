package com.codeheist.controller;

import com.codeheist.model.Employee;
import com.codeheist.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
//----------------------------------------------------------HOME PAGE---------------------------------------------------
    @GetMapping("/register")
    public String showRegPage(Model model){
        model.addAttribute("employee",new Employee());
        //return "EmployeeRegisterPage";
        return "EmployeeRegisterPageUpdated";
    }
//----------------------------------------------------------Save Employee-----------------------------------------------
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee,Model model){
            Integer eid=employeeService.saveEmployee(employee);
            model.addAttribute("msg","Employee '"+eid+"' Saved");
            //BACKING OBJECT
            model.addAttribute("employee",new Employee());
            return "EmployeeRegisterPageUpdated";
    }
//------------------------------------------------------------DATA PAGE-------------------------------------------------

/*
    //All Records in Single
    @GetMapping("/all")
    public String showData(Model model){
        List<Employee> employees=employeeService.getAllEmployee();
        model.addAttribute("empsList",employees);
        return "EmployeeDataPage";
    }
*/
     //-----------------------------PAGINATION----------------------------------------------
     @GetMapping("/all")
     public String showDataInPages(@PageableDefault(page = 0,size = 3) Pageable pageable , Model model){
         Page<Employee> page=employeeService.getAllEmployee(pageable);
         model.addAttribute("empsList",page.getContent());
         model.addAttribute("page",page);
         return "EmployeeDataPage";
     }

//------------------------------------------------------------EDIT PAGE-------------------------------------------------

    @GetMapping("/edit")
    public String editPage(@RequestParam Integer eid,Model model){
        Employee employee=employeeService.getOneEmployee(eid);
        model.addAttribute("employee",employee);
        return "EmployeeEditPageUpdated";
    }

    @PostMapping("/update")
    public  String updateEmployee(@ModelAttribute Employee employee){
        employeeService.updateEmployee(employee);
        return "redirect:all";
    }
//-----------------------------------------------------------Delete-----------------------------------------------------

    @GetMapping("/delete")
    public String delete(@RequestParam Integer eid){
        employeeService.deleteEmployee(eid);
        return "redirect:all";
    }

//-----------------------------------------------------------AJAX METHOD CALL-----------------------------------------------------
    @GetMapping("/checkEmail")
    public @ResponseBody String validateEmail(@RequestParam String email){
         String message=" ";
        System.out.println("message not created yet");
        if(employeeService.isEmployeeExistByEmail(email)){
            message=email+" Already exist";
        }
        System.out.println("message created");
         return message;
    }
}
