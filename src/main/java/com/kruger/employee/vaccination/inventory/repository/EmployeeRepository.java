package com.kruger.employee.vaccination.inventory.repository;

import com.kruger.employee.vaccination.inventory.entity.Employee;
import com.kruger.employee.vaccination.inventory.entity.VaccinationStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findByIdentification(Long identification);

  List<Employee> findByVaccinationStatus(VaccinationStatus vaccinationStatus);

  @Query(
      value =
          "SELECT * \n"
              + "FROM employees\n"
              + "INNER JOIN employee_vaccines\n"
              + "ON employees.employee_id = employee_vaccines.vaccine_employee_id\n"
              + "WHERE vaccine_type = ?1 ;",
      nativeQuery = true
  )
  List<Employee> findByVaccineType(Integer vaccineType);
}
