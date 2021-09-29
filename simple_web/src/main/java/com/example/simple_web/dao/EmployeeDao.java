package com.example.simple_web.dao;

import com.example.simple_web.pojo.Department;
import com.example.simple_web.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(1001, new Employee(1001, "Jackson", "helloworld1001@hello.com", 0, new Department(101, "教学部"), new Date()));
        employees.put(1002, new Employee(1002, "Anny", "helloworld1002@hello.com", 1, new Department(101, "市场部"), new Date()));
        employees.put(1003, new Employee(1003, "Randell", "helloworld1003@hello.com", 0, new Department(101, "研究部"), new Date()));
        employees.put(1004, new Employee(1004, "Bob", "helloworld1004@hello.com", 1, new Department(101, "运营部"), new Date()));
        employees.put(1005, new Employee(1005, "James", "helloworld1005@hello.com", 0, new Department(101, "教学部"), new Date()));
    }


    private static Integer initID = 1006;

    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initID++);
        }

        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee getEmployee(Integer id) {
        return employees.get(id);
    }

    public void delete(Integer id) {
        employees.remove(id);
    }
}
