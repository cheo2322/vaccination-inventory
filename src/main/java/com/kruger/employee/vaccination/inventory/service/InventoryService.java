package com.kruger.employee.vaccination.inventory.service;

import com.kruger.employee.vaccination.inventory.domain.dto.UserDto;
import com.kruger.employee.vaccination.inventory.domain.dto.UserGetResponse;
import com.kruger.employee.vaccination.inventory.domain.dto.UserPostResponse;
import com.kruger.employee.vaccination.inventory.entity.User;
import com.kruger.employee.vaccination.inventory.entity.VaccinationStatus;
import com.kruger.employee.vaccination.inventory.entity.Vaccine;
import com.kruger.employee.vaccination.inventory.entity.VaccineType;
import com.kruger.employee.vaccination.inventory.mapper.UserMapper;
import com.kruger.employee.vaccination.inventory.repository.UserRepository;
import com.kruger.employee.vaccination.inventory.security.UserDetailsImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InventoryService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Could not find user."));

    return new UserDetailsImpl(user);
  }

  private String generateUsername(UserDto userDto) {
    return userDto.getLastName().substring(0, 1)
        .concat(userDto.getFirstName().substring(0, 1))
        .concat(userDto.getIdentification());
  }

  public UserPostResponse createEmployee(UserDto userDto) {
    User employee = UserMapper.INSTANCE.dtoToEmployee(userDto);
    employee.setUsername(generateUsername(userDto));
    employee.setPassword(getEncryptedPassword());

    User user = userRepository.save(employee);

    return UserMapper.INSTANCE.employeeToPostResponse(user);
  }

  private String getEncryptedPassword() {
    return BCrypt.hashpw(UUID.randomUUID().toString().split("-")[0], BCrypt.gensalt());
  }

  public void updateEmployee(UserDto userDto, String id) {
    User user = userRepository.findByIdentification(Long.valueOf(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    UserMapper.INSTANCE.updateEmployeeFromDto(userDto, user);

    List<Vaccine> vaccines = new ArrayList<>();
    if (Objects.nonNull(user.getVaccines())) {
      vaccines = user.getVaccines();
    }

    vaccines.addAll(Objects.nonNull(userDto.getVaccines()) ?
        userDto.getVaccines() :
        List.of());

    user.setVaccines(vaccines);

    userRepository.save(user);
  }

  public UserGetResponse getEmployeeById(String id) {
    return userRepository.findByIdentification(Long.valueOf(id))
        .map(UserMapper.INSTANCE::employeeToResponse)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public List<UserGetResponse> getEmployees() {
    return userRepository.findAll()
        .stream().map(UserMapper.INSTANCE::employeeToResponse)
        .collect(Collectors.toList());
  }

  public void deleteEmployee(String id) {
    User user = userRepository.findByIdentification(Long.valueOf(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    userRepository.delete(user);
  }

  public Set<UserGetResponse> getEmployeesByVaccinationStatus(String status) {
    return userRepository.findByVaccinationStatus(VaccinationStatus.valueOf(status))
        .stream().map(UserMapper.INSTANCE::employeeToResponse)
        .collect(Collectors.toSet());
  }

  public Set<UserGetResponse> getEmployeesByVaccinationType(String vaccineType) {
    return userRepository.findByVaccineType(VaccineType.valueOf(vaccineType).ordinal())
        .stream().map(UserMapper.INSTANCE::employeeToResponse)
        .collect(Collectors.toSet());
  }

  public Set<UserGetResponse> getEmployeesInARangeDate(String startDate,
      String finalDate) {

    return userRepository
        .findByVaccinationDate(LocalDate.parse(startDate), LocalDate.parse(finalDate))
        .stream().map(UserMapper.INSTANCE::employeeToResponse)
        .collect(Collectors.toSet());
  }
}
