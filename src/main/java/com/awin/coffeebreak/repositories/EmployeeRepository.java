package com.awin.coffeebreak.repositories;

import com.awin.coffeebreak.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}