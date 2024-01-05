package com.jasperproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jasperproj.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query(value = "select * from employee e where e.id =:key or e.name=:key or e.role=:key or e.salary =:key ",nativeQuery = true)
	List<Employee> findBykey(Object key);

	
	@Query(value = "select * from employee e where e.id =:key or e.name=:key or e.role=:key or e.salary =:key ",nativeQuery= true)
	List<Employee> getfilter(Object key);
}

