package com.kruger.employee.vaccination.inventory.domain;

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
@Table(name = "admins")
public class Administrator {

  @Id
  @Column(name = "admin_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
