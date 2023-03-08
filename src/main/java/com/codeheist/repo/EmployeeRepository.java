package com.codeheist.repo;

import com.codeheist.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT count(empEmail) from Employee where empEmail=:email")
    public Integer getEmailCount(String email);
}
