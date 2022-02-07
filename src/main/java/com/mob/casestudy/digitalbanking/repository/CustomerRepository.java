package com.mob.casestudy.digitalbanking.repository;

import com.mob.casestudy.digitalbanking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByUserName(String userName);
    boolean existsByUserName(String userName);
}
