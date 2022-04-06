package com.kruger.employee.vaccination.inventory.domain.dto;

import com.kruger.employee.vaccination.inventory.entity.VaccinationStatus;
import com.kruger.employee.vaccination.inventory.entity.Vaccine;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

  @NotBlank
  @Length(min = 10, max = 10)
  private String identification;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @Email
  private String email;

  private LocalDate bornDate;
  private String address;
  private String mobilePhone;
  private VaccinationStatus vaccinationStatus;
  private List<Vaccine> vaccines;
}
