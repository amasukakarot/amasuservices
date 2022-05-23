package com.amasu.fraud.service;

import com.amasu.fraud.model.FraudCheckHistory;
import com.amasu.fraud.repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class FraudService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(true)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
