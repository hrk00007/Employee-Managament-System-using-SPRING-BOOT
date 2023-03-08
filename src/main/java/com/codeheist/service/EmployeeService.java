package com.codeheist.service;

import com.codeheist.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    public Integer saveEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void  deleteEmployee(Integer empId);
    public Employee getOneEmployee(Integer empId);
    public List<Employee> getAllEmployee();
    public Page<Employee> getAllEmployee(Pageable pageable);

    public boolean isEmployeeExistByEmail(String email);

}
