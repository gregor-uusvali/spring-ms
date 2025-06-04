package com.example.springMSorder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springMSorder.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
