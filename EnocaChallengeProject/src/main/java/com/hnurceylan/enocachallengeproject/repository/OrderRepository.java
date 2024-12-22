package com.hnurceylan.enocachallengeproject.repository;

import com.hnurceylan.enocachallengeproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Optional<Order> findByOrderCode(String orderCode);

    List<Order> findByCustomerId(int customerId);

}
