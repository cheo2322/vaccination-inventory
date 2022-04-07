package com.kruger.employee.vaccination.inventory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGetResponse {

  private String identification;
  private String firstName;
  private String lastName;
  private String email;
}
