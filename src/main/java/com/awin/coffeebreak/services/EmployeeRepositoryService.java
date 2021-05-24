package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.Employee;
import com.awin.coffeebreak.exceptions.EmployeeNotFoundException;
import com.awin.coffeebreak.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeRepositoryService {

    private EmployeeRepository employeeRepository;

    public EmployeeRepositoryService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * This method will return the employee identified by the ID supplied, or throws an exception if it cannot be found.
     *
     * @param id the ID of the employee to search for
     * @return The employee object as found
     * @throws EmployeeNotFoundException in the event the employee cannot be found
     */
    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(EmployeeNotFoundException::new);
    }

}
