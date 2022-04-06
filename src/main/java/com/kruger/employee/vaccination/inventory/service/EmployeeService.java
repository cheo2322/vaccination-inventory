package com.kruger.employee.vaccination.inventory.service;

import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeDto;
import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeGetResponse;
import com.kruger.employee.vaccination.inventory.domain.dto.EmployeePostResponse;
import com.kruger.employee.vaccination.inventory.entity.Employee;
import com.kruger.employee.vaccination.inventory.entity.VaccinationStatus;
import com.kruger.employee.vaccination.inventory.entity.VaccineType;
import com.kruger.employee.vaccination.inventory.mapper.EmployeeMapper;
import com.kruger.employee.vaccination.inventory.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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

  public EmployeePostResponse createEmployee(EmployeeDto employeeDto) {
    Employee employee = employeeRepository.save(EmployeeMapper.INSTANCE.dtoToEmployee(employeeDto));

    EmployeePostResponse response = EmployeeMapper.INSTANCE.employeeToPostResponse(employee);
    response.setPassword(UUID.randomUUID().toString().split("-")[0]);

    return response;
  }

  public void updateEmployee(EmployeeDto employeeDto, String id) {
    Employee employee = employeeRepository.findByIdentification(Long.valueOf(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    EmployeeMapper.INSTANCE.updateEmployeeFromDto(employeeDto, employee);
    employee.getVaccines().addAll(Objects.nonNull(employeeDto.getVaccines()) ?
        employeeDto.getVaccines() :
        List.of());

    employeeRepository.save(employee);
  }

  public EmployeeGetResponse getEmployeeById(String id) {
    return employeeRepository.findByIdentification(Long.valueOf(id))
        .map(EmployeeMapper.INSTANCE::employeeToResponse)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public List<EmployeeGetResponse> getEmployees() {
    return employeeRepository.findAll()
        .stream().map(EmployeeMapper.INSTANCE::employeeToResponse)
        .collect(Collectors.toList());
  }

  public void deleteEmployee(String id) {
    Employee employee = employeeRepository.findByIdentification(Long.valueOf(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    employeeRepository.delete(employee);
  }

  public Set<EmployeeGetResponse> getEmployeesByVaccinationStatus(String status) {
    return employeeRepository.findByVaccinationStatus(VaccinationStatus.valueOf(status))
        .stream().map(EmployeeMapper.INSTANCE::employeeToResponse)
        .collect(Collectors.toSet());
  }

  public Set<EmployeeGetResponse> getEmployeesByVaccinationType(String vaccineType) {
    return employeeRepository.findByVaccineType(VaccineType.valueOf(vaccineType).ordinal())
        .stream().map(EmployeeMapper.INSTANCE::employeeToResponse)
        .collect(Collectors.toSet());
  }

  public Set<EmployeeGetResponse> getEmployeesInARangeDate(String startDate,
      String finalDate) {

    return employeeRepository
        .findByVaccinationDate(LocalDate.parse(startDate), LocalDate.parse(finalDate))
        .stream().map(EmployeeMapper.INSTANCE::employeeToResponse)
        .collect(Collectors.toSet());
  }
}
