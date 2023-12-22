package com.ericksena.backendbarbeariatrainee.api.assembler;

import java.util.Base64;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ericksena.backendbarbeariatrainee.api.model.CustomerDTO;
import com.ericksena.backendbarbeariatrainee.api.model.CustomerRegisterDTO;
import com.ericksena.backendbarbeariatrainee.domain.model.Customer;
import com.ericksena.backendbarbeariatrainee.domain.model.User;

@Component
public class CustomerAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO toModel(Customer customer) {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        customerDTO.setName(customer.getUser().getName());
        String credential = Base64.getEncoder().encodeToString(
                (customer.getUser().getEmail() + ":" + customer.getUser().getPassword()).getBytes());
        customerDTO.setCredential(credential);
        return customerDTO;
    }

    public CustomerRegisterDTO toRegisterModel(Customer customer) {
        return modelMapper.map(customer, CustomerRegisterDTO.class);
    }

    public Customer toEntity(CustomerRegisterDTO customerRegisterDTO) {
        Customer mappedCustomer = modelMapper.map(customerRegisterDTO, Customer.class);
        User mappedUser = modelMapper.map(customerRegisterDTO, User.class);
        mappedCustomer.setUser(mappedUser);
        return mappedCustomer;
    }
}
