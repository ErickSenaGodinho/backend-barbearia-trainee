package com.ericksena.backendbarbeariatrainee.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ericksena.backendbarbeariatrainee.api.assembler.CustomerAssembler;
import com.ericksena.backendbarbeariatrainee.api.model.CustomerDTO;
import com.ericksena.backendbarbeariatrainee.api.model.CustomerRegisterDTO;
import com.ericksena.backendbarbeariatrainee.domain.model.Customer;
import com.ericksena.backendbarbeariatrainee.domain.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerAssembler customerAssembler;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@RequestBody CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = customerAssembler.toEntity(customerRegisterDTO);
        Customer savedCustomer = customerService.create(customer);
        return customerAssembler.toModel(savedCustomer);
    }

    @PutMapping(value = "/{id}")
    public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = customerAssembler.toEntity(customerRegisterDTO);
        customer.setId(id);
        Customer updatedCustomer = customerService.update(customer);
        return customerAssembler.toModel(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable Long id) {
        customerService.deleteById(id);
    }

}
