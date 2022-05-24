package com.amasu.customer.service;

import com.amasu.customer.exception.CustomerIsFraudException;
import com.amasu.customer.model.Customer;
import com.amasu.customer.model.CustomerRegistrationRequest;
import com.amasu.customer.model.FraudCheckResponse;
import com.amasu.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    @Value("${app.url}")
    private String fraudServiceUrl;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .surname(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        log.info("customer {}", customer);
        customerRepository.saveAndFlush(customer);
        checkIfCustomerIsFraudster(customer.getId());

        // todo: check if email valid
        // todo: check if email is not taken
        // todo: store customer in db
        // todo: check if fraudster
        // todo: send notification

    }

    private boolean checkIfCustomerIsFraudster(Integer customerId) {
        log.info("url {}",fraudServiceUrl);
        FraudCheckResponse fraudCheckResponseResponse =
                restTemplate.getForObject(fraudServiceUrl + customerId,FraudCheckResponse.class);
        if(!fraudCheckResponseResponse.isFraudster()) {
            return fraudCheckResponseResponse.isFraudster();
        } else {
            throw new CustomerIsFraudException("Fraudster");
        }
    }
}
