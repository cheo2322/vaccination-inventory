package com.kruger.employee.vaccination.inventory.mapper;

import com.kruger.employee.vaccination.inventory.domain.dto.UserDto;
import com.kruger.employee.vaccination.inventory.domain.dto.UserGetResponse;
import com.kruger.employee.vaccination.inventory.domain.dto.UserPostResponse;
import com.kruger.employee.vaccination.inventory.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "username", qualifiedByName = "generateUsername", ignore = true)
  UserPostResponse employeeToPostResponse(User user);

  @Mapping(target = "username", qualifiedByName = "generateUsernameDto", ignore = true)
  User dtoToEmployee(UserDto userDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "vaccines", ignore = true)
  void updateEmployeeFromDto(UserDto userDto, @MappingTarget User user);

  @Mapping(source = "identification", target = "identification", qualifiedByName = "addZero")
  UserGetResponse employeeToResponse(User user);

  @Named("addZero")
  default String addZero(Long id) {
    String stringId = String.valueOf(id);
    if (stringId.length() == 9) {
      stringId = "0" + stringId;
    }

    return stringId;
  }

  @Named("generateUsername")
  default String generateUsername(User user) {
    return user.getLastName().substring(0, 1)
        .concat(user.getFirstName().substring(0, 1))
        .concat(user.getIdentification().toString());
  }

  @Named("generateUsernameDto")
  default String generateUsernameDto(UserDto userDto) {
    return userDto.getLastName().substring(0, 1)
        .concat(userDto.getFirstName().substring(0, 1))
        .concat(userDto.getIdentification());
  }
}
