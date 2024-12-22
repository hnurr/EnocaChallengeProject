package com.hnurceylan.enocachallengeproject.repository;

import com.hnurceylan.enocachallengeproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByMail(String mail);
}
