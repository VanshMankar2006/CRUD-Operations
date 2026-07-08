package com.shivansh.SprinBoot_CRUD.repository;

import com.shivansh.SprinBoot_CRUD.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

     public Optional<Customer> findByIdAndIsDeletedIsFalse(Long id);

     public List<Customer> findAllByIsDeletedFalse();

    public boolean existsByEmail(String email);

}
