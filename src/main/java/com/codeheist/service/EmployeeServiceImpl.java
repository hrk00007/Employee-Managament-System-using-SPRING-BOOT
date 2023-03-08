package com.codeheist.service;
import com.codeheist.exception.EmployeeNotFoundException;
import com.codeheist.model.Employee;
import com.codeheist.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Integer saveEmployee(Employee employee) {
        try {
            double esal= employee.getEmpSal();
            double hra=esal * 20/100.0;
            double da=esal *10/100.0;
            employee.setHra(hra);
            employee.setDa(da);
            Integer eid=employeeRepository.save(employee).getEmpId();
            return eid;
        } catch (EmployeeNotFoundException e) {
            throw  e;
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        saveEmployee(employee);
    }

    @Override
    public void deleteEmployee(Integer empId) {
         employeeRepository.deleteById(empId);
    }

    @Override
    public Employee getOneEmployee(Integer empId) {
        return employeeRepository.findById(empId).get();
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> list=employeeRepository.findAll();
        return list;
    }

    @Override
    public Page<Employee> getAllEmployee(Pageable pageable) {
        Page<Employee> page=employeeRepository.findAll(pageable);
        return page;
    }

    @Override
    public boolean isEmployeeExistByEmail(String email) {
        return employeeRepository.getEmailCount(email)>0;
    }

}
