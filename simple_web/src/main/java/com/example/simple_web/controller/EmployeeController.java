package com.example.simple_web.controller;

import com.example.simple_web.dao.DepartmentDao;
import com.example.simple_web.dao.EmployeeDao;
import com.example.simple_web.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    EmployeeDao employeeDao;
    DepartmentDao departmentDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @RequestMapping("/allEmployee")
    public String employeeList(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employeeList", employees);
        return "emp/employeeList";
    }

    @GetMapping("/addEmployeeAction")
    public String addEmployeeActionGet(Model model) {
        model.addAttribute("departmentList", departmentDao.getDepartments());
        return "emp/addEmployeePage";
    }

    @PostMapping("/addEmployeeAction")
    public String addEmployeeActionPost(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/allEmployee";
    }

    @RequestMapping("/deleteEmployeeAction")
    public String deleteEmployeeAction(int employeeId) {
        employeeDao.delete(employeeId);
        return "redirect:/allEmployee";
    }

    @RequestMapping("/editEmployeePage")
    public String editEmployeePage(int employeeId, Model model) {
        Employee employee = employeeDao.getEmployee(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("departmentList", departmentDao.getDepartments());
        return "emp/editEmployeePage";
    }

}
