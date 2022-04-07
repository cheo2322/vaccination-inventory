package com.kruger.employee.vaccination.inventory.repository;

import com.kruger.employee.vaccination.inventory.entity.User;
import com.kruger.employee.vaccination.inventory.entity.VaccinationStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByIdentification(Long identification);

  Optional<User> findByUsername(String username);

  List<User> findByVaccinationStatus(VaccinationStatus vaccinationStatus);

  @Query(
      value = "SELECT *\n"
          + "FROM users\n"
          + "INNER JOIN user_vaccines\n"
          + "ON users.user_id = user_vaccines.user_id\n"
          + "WHERE vaccine_type = ?1 ;",
      nativeQuery = true
  )
  List<User> findByVaccineType(Integer vaccineType);

  @Query(
      value = "SELECT *\n"
          + "FROM users e\n"
          + "INNER JOIN user_vaccines e_v\n"
          + "ON e.user_id = e_v.user_id\n"
          + "WHERE e_v.vaccination_date >= ?1 \n"
          + "AND e_v.vaccination_date <= ?2 ",
      nativeQuery = true
  )
  List<User> findByVaccinationDate(LocalDate startDate, LocalDate finalDate);
}
