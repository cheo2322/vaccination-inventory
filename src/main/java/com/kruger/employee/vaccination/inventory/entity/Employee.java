package com.kruger.employee.vaccination.inventory.entity;

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

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String email;
}
