package com.kruger.employee.vaccination.inventory.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.kruger.employee.vaccination.inventory.domain.dto.UserDto;
import com.kruger.employee.vaccination.inventory.domain.dto.UserGetResponse;
import com.kruger.employee.vaccination.inventory.domain.dto.UserPostResponse;
import com.kruger.employee.vaccination.inventory.entity.Vaccine;
import com.kruger.employee.vaccination.inventory.entity.VaccineType;
import com.kruger.employee.vaccination.inventory.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class InventoryServiceTest {

  private static UserDto userDto;

  @Autowired
  InventoryService inventoryService;

  @Autowired
  UserRepository userRepository;

  @BeforeEach
  void setUp() {
    userDto = UserDto.builder()
        .identification("1")
        .firstName("f")
        .lastName("l")
        .email("x@y.z")
        .build();
  }

  @Test
  @Rollback
  void shouldPostANewUser() {
    UserPostResponse userPostResponse = inventoryService.createEmployee(userDto);

    assertThat(userPostResponse.getUsername(), equalTo("lf1"));
    assertThat(userPostResponse.getPassword(), notNullValue());
  }

  @Test
  @Rollback
  void shouldUpdateAnUser() {
    UserDto userDto1 = userDto;
    userDto1.setIdentification("2");
    inventoryService.createEmployee(userDto1);

    Vaccine vaccine = new Vaccine();
    vaccine.setVaccineType(VaccineType.PFIZER);
    vaccine.setDosesNumber(1);
    vaccine.setVaccinationDate(LocalDate.parse("2020-09-20"));

    UserDto userDto2 = UserDto.builder()
        .lastName("LastName")
        .email("email@example.com")
        .vaccines(List.of(vaccine))
        .build();
    inventoryService.updateEmployee(userDto2, "2");

    UserGetResponse userGetResponse = inventoryService.getEmployeeById("2");

    assertThat(userGetResponse.getLastName(), equalTo("LastName"));
  }

  @Test
  void getEmployeeById() {
  }

  @Test
  void getEmployees() {
  }

  @Test
  void deleteEmployee() {
  }

  @Test
  void getEmployeesByVaccinationStatus() {
  }

  @Test
  void getEmployeesByVaccinationType() {
  }

  @Test
  void getEmployeesInARangeDate() {
  }
}