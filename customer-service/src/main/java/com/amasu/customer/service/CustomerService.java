package com.amasu.customer.service;

import com.amasu.customer.exception.CustomerIsFraudException;
import com.amasu.customer.model.Customer;
import com.amasu.customer.model.CustomerRegistrationRequest;
import com.amasu.customer.model.FraudCheckResponse;
import com.amasu.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    @Value("${app.url}")
    private String appUrl;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .surname(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

//        log.info("appurl: {}", appUrl);
        log.info("here {}", customer);
        // todo: check if email valid
        // todo: check if email is not taken
        // todo: store customer in db
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster
        // todo: send notification

        checkIfCustomerIsFraudster(customer.getId());

    }

    private boolean checkIfCustomerIsFraudster(Integer customerId) {
        String url = "http://fraud-service:30001/api/v1/fraud-check/";
        log.info("url {}",url);
        FraudCheckResponse fraudCheckResponseResponse =
                restTemplate.getForObject(url + customerId,FraudCheckResponse.class);
        if(!fraudCheckResponseResponse.isFraudster()) {
            return fraudCheckResponseResponse.isFraudster();
        } else {
            throw new CustomerIsFraudException("Fraudster");
        }
    }
}
