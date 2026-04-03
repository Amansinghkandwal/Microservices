package com.designX.accounts.repository;

import com.designX.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

     Optional<Customer> findByMobileNumber(String mobileNumber);
}
