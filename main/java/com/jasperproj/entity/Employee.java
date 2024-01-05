package com.jasperproj.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="employee")

public class Employee {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="id")
	private Long empId;
	
	@Column(name="name")
	private String empName;
	
	@Column(name="role")
	private String empRole;
	
	@Column(name="salary")
	private int empSalary;

}
