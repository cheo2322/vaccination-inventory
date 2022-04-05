package com.kruger.employee.vaccination.inventory.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee_vaccines")
public class Vaccine {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "vaccine_id", nullable = false)
  private Long id;

  private VaccineType vaccineType;
  private LocalDate vaccinationDate;
  private Integer dosesNumber;
}
