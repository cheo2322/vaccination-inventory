package com.kruger.employee.vaccination.inventory.mapper;

import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeDto;
import com.kruger.employee.vaccination.inventory.domain.dto.EmployeeGetResponse;
import com.kruger.employee.vaccination.inventory.entity.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

  Employee dtoToEmployee(EmployeeDto employeeDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEmployeeFromDto(EmployeeDto employeeDto, @MappingTarget Employee employee);

  @Mapping(source = "identification", target = "identification", qualifiedByName = "addZero")
  EmployeeGetResponse employeeToResponse(Employee employee);

  @Named("addZero")
  default String addZero(Long id) {
    String stringId = String.valueOf(id);
    if (stringId.length() == 9) {
      stringId = "0" + stringId;
    }

    return stringId;
  }
}
