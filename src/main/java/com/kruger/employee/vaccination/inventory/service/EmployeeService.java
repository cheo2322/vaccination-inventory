package com.kruger.employee.vaccination.inventory.service;

import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeDto;
import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeResponse;
import com.kruger.employee.vaccination.inventory.entity.Employee;
import com.kruger.employee.vaccination.inventory.entity.VaccinationStatus;
import com.kruger.employee.vaccination.inventory.entity.VaccineType;
import com.kruger.employee.vaccination.inventory.mapper.EmployeeMapper;
import com.kruger.employee.vaccination.inventory.repository.EmployeeRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  EmployeeMapper employeeMapper;

  public EmployeeResponse createEmployee(EmployeeDto employeeDto) {
    Employee employee = employeeRepository.save(employeeMapper.dtoToEmployee(employeeDto));

    return EmployeeResponse.builder()
        .userName(employee.getIdentification().toString())
        .password(UUID.randomUUID().toString().split("-")[0])
        .build();
  }

  public void updateEmployee(EmployeeDto employeeDto, String id) {
    Employee employee = employeeRepository.findByIdentification(Long.valueOf(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    employeeMapper.updateEmployeeFromDto(employeeDto, employee);
    employee.getVaccines().add(employeeDto.getVaccine());
    employeeRepository.save(employee);
  }

  public EmployeeDto getEmployeeById(String id) {
    return employeeRepository.findByIdentification(Long.valueOf(id))
        .map(employeeMapper::employeeToDto)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public List<EmployeeDto> getEmployees() {
    return employeeRepository.findAll()
        .stream().map(employee -> employeeMapper.employeeToDto(employee))
        .collect(Collectors.toList());
  }

  public void deleteEmployee(String id) {
    Employee employee = employeeRepository.findByIdentification(Long.valueOf(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    employeeRepository.delete(employee);
  }

  public List<EmployeeDto> getEmployeesByVaccinationStatus(String status) {
    return employeeRepository.findByVaccinationStatus(VaccinationStatus.valueOf(status))
        .stream().map(employeeMapper::employeeToDto)
        .collect(Collectors.toList());
  }

  public List<EmployeeDto> getEmployeesByVaccinationType(String vaccineType) {
    return employeeRepository.findByVaccineType(VaccineType.valueOf(vaccineType).ordinal())
        .stream().map(employeeMapper::employeeToDto)
        .collect(Collectors.toList());
  }
}
