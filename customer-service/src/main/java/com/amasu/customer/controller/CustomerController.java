package com.amasu.customer.controller;

import com.amasu.customer.model.CustomerRegistrationRequest;
import com.amasu.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Value("${app.message}")
    private String helloMessage;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("New customer registration {}", customerRegistrationRequest);
        log.info("StringTest= " + helloMessage);
        customerService.registerCustomer(customerRegistrationRequest);
    }


}
