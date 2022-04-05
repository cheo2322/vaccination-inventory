package com.kruger.employee.vaccination.inventory.repository;

import com.kruger.employee.vaccination.inventory.entity.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findByIdentification(Long identification);
}
