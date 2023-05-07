package Employer.angular.SpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Employer.angular.SpringBoot.model.Employee;

@Repository
public interface EmployeeRepository extends  JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.emailId =:eml AND e.password =:password")
    public Employee getEmpByLogin(@Param("eml") String eml,@Param("password") String password);
    
    @Query("SELECT e FROM Employee e WHERE e.emailId =:email ")
    public Employee findByUsername(@Param("email") String email);
	
}
