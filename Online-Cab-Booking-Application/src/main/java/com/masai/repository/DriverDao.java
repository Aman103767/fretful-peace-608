package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;
import com.masai.model.Driver;

@Repository
public interface DriverDao extends JpaRepository<Driver,Integer>{
	
	public Driver findByMobileNumber(String mobileNo);
	
    @Query("from Customer c where c.mobileNumber = ?1")
	public Customer customerByMobileNumber(String mobileNo);

}