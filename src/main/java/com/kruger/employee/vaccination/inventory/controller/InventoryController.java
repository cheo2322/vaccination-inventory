package com.kruger.employee.vaccination.inventory.controller;

import com.kruger.employee.vaccination.inventory.domain.dto.UserDto;
import com.kruger.employee.vaccination.inventory.domain.dto.UserGetResponse;
import com.kruger.employee.vaccination.inventory.domain.dto.UserPostResponse;
import com.kruger.employee.vaccination.inventory.service.InventoryService;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InventoryController {

  @Autowired
  InventoryService inventoryService;

  @PostMapping("/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public UserPostResponse postEmployee(@RequestBody @Valid UserDto userDto) {
    return inventoryService.createEmployee(userDto);
  }

  @PatchMapping("/employees/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void patchEmployee(@RequestBody UserDto userDto, @PathVariable String id) {
    inventoryService.updateEmployee(userDto, id);
  }

  @GetMapping("/employees/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserGetResponse getEmployee(@PathVariable String id) {
    return inventoryService.getEmployeeById(id);
  }

  @GetMapping("/employees")
  @ResponseStatus(HttpStatus.OK)
  public List<UserGetResponse> getEmployees() {
    return inventoryService.getEmployees();
  }

  @DeleteMapping("/employees")
  @ResponseStatus(HttpStatus.OK)
  public void deleteEmployee(String id) {
    inventoryService.deleteEmployee(id);
  }

  @GetMapping("/employees/vaccine/status")
  @ResponseStatus(HttpStatus.OK)
  public Set<UserGetResponse> getEmployeesByVaccinationStatus(
      @RequestParam String vaccinationStatus) {

    return inventoryService.getEmployeesByVaccinationStatus(vaccinationStatus);
  }

  @GetMapping("/employees/vaccine/type")
  @ResponseStatus(HttpStatus.OK)
  public Set<UserGetResponse> getEmployeesByVaccineType(@RequestParam String vaccineType) {
    return inventoryService.getEmployeesByVaccinationType(vaccineType);
  }

  @GetMapping("/employees/vaccine/date")
  @ResponseStatus(HttpStatus.OK)
  public Set<UserGetResponse> getEmployeesInADateRange(@RequestParam String startDate,
      @RequestParam String finalDate) {

    return inventoryService.getEmployeesInARangeDate(startDate, finalDate);
  }
}
