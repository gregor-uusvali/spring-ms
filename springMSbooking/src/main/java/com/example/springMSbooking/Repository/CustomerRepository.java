package com.example.springMSbooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springMSbooking.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
