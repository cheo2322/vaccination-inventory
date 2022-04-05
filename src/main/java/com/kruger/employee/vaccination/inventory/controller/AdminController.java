package com.kruger.employee.vaccination.inventory.controller;

import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeDto;
import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeResponse;
import com.kruger.employee.vaccination.inventory.service.AdminService;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  AdminService adminService;

  @PostMapping("/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public EmployeeResponse postEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
    return adminService.createEmployee(employeeDto);
  }

  @PatchMapping("/employees/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void patchEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable String id) {
    adminService.updateDto(employeeDto, id);
  }

  @GetMapping("/employees/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EmployeeDto getEmployee(@PathVariable String id) {
    return adminService.getEmployeeById(id);
  }

  @GetMapping("/employees")
  @ResponseStatus(HttpStatus.OK)
  public List<EmployeeDto> getEmployees() {
    return adminService.getEmployees();
  }

  @DeleteMapping("/employees")
  @ResponseStatus(HttpStatus.OK)
  public void deleteEmployee(String id) {
    adminService.deleteEmployee(id);
  }
}
