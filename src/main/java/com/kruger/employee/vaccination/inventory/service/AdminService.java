package com.kruger.employee.vaccination.inventory.service;

import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeDto;
import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeResponse;
import com.kruger.employee.vaccination.inventory.entity.Employee;
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
public class AdminService {

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

  public void updateDto(EmployeeDto employeeDto, String id) {
    Employee employee = employeeRepository.findByIdentification(Long.valueOf(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    employeeMapper.updateEmployeeFromDto(employeeDto, employee);
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
}
