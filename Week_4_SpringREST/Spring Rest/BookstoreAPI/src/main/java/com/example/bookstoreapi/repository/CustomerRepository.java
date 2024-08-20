package com.example.bookstoreapi.repository;

import com.example.bookstoreapi.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}