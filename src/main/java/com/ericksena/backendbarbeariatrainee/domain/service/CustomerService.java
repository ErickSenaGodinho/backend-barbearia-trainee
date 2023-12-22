package com.ericksena.backendbarbeariatrainee.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ericksena.backendbarbeariatrainee.api.assembler.CustomerAssembler;
import com.ericksena.backendbarbeariatrainee.api.model.CustomerDTO;
import com.ericksena.backendbarbeariatrainee.domain.model.Customer;
import com.ericksena.backendbarbeariatrainee.domain.model.User;
import com.ericksena.backendbarbeariatrainee.domain.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAssembler customerAssembler;

    @Autowired
    private UserService userService;

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "CustomerId Invalid"));
    }

    @Transactional
    public Customer create(Customer customer) {
        Customer foundCustomer = findByCpf(customer.getCpf());
        if (foundCustomer != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF already used");
        }
        User createdUser = userService.create(customer.getUser());
        createdUser.getRoles().add("CUSTOMER");
        customer.setUser(createdUser);
        return customerRepository.save(customer);
    }

    @Transactional
    public CustomerDTO update(Customer customer, String authHeader) {
        Customer foundCustomer = customerRepository.findById(customer.getId()).stream().findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found"));

        Long userId = foundCustomer.getUser().getId();
        customer.getUser().setId(userId);
        Customer foundCustomerByCpf = findByCpf(customer.getCpf());
        if (foundCustomerByCpf != null && customer.getId() != foundCustomerByCpf.getId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF already used");
        }
        User updatedUser = userService.update(customer.getUser(), authHeader);
        customer.setUser(updatedUser);
        return customerAssembler.toModel(customerRepository.save(customer));
    }

    private Customer findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf).stream().findFirst().orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
