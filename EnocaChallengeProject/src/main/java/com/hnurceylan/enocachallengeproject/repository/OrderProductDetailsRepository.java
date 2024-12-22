package com.hnurceylan.enocachallengeproject.repository;

import com.hnurceylan.enocachallengeproject.entity.OrderProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductDetailsRepository extends JpaRepository<OrderProductDetails,Integer> {

}
