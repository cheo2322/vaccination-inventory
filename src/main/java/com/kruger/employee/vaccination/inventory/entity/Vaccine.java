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
@Table(name = "user_vaccines")
public class Vaccine {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "vaccine_id", nullable = false)
  private Long id;

  @Column(name = "vaccine_type", nullable = false)
  private VaccineType vaccineType;

  @Column(name = "vaccination_date", nullable = false)
  private LocalDate vaccinationDate;

  @Column(name = "doses_number", nullable = false)
  private Integer dosesNumber;
}
