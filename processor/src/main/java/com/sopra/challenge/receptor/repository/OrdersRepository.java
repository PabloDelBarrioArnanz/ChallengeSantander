package com.sopra.challenge.receptor.repository;

import com.sopra.challenge.receptor.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    List<Order> findByCity(String city);

}
