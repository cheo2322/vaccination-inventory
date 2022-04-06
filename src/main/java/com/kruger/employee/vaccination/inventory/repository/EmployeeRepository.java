package com.kruger.employee.vaccination.inventory.repository;

import com.kruger.employee.vaccination.inventory.entity.Employee;
import com.kruger.employee.vaccination.inventory.entity.VaccinationStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findByIdentification(Long identification);

  List<Employee> findByVaccinationStatus(VaccinationStatus vaccinationStatus);

  @Query(
      value = "SELECT *\n"
          + "FROM employees\n"
          + "INNER JOIN employee_vaccines\n"
          + "ON employees.employee_id = employee_vaccines.employee_id\n"
          + "WHERE vaccine_type = ?1 ;",
      nativeQuery = true
  )
  List<Employee> findByVaccineType(Integer vaccineType);

  @Query(
      value = "SELECT *\n"
          + "FROM employees e\n"
          + "INNER JOIN employee_vaccines e_v\n"
          + "ON e.employee_id = e_v.employee_id\n"
          + "WHERE e_v.vaccination_date >= ?1 \n"
          + "AND e_v.vaccination_date <= ?2 ",
      nativeQuery = true
  )
  List<Employee> findByVaccinationDate(LocalDate startDate, LocalDate finalDate);
}
