package com.ericksena.backendbarbeariatrainee.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ericksena.backendbarbeariatrainee.domain.model.Customer;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByCpf(String cpf);
}
