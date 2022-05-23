package com.amasu.fraud.controller;

import com.amasu.fraud.model.FraudCheckResponse;
import com.amasu.fraud.service.FraudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    private final FraudService fraudService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraudulentCustomer = fraudService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
