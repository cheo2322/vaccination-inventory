package com.kruger.employee.vaccination.inventory.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "employee_id", nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private Long identification;

  @Column(nullable = false)
  private String name;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String email;

  @Column(name = "born_date")
  private LocalDate bornDate;
  private String address;
  private String mobilePhone;
  private VaccinationState vaccinationState;

  @OneToMany(targetEntity = Vaccine.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "vaccine_employee_id", referencedColumnName = "employee_id")
  private List<Vaccine> vaccines;
}