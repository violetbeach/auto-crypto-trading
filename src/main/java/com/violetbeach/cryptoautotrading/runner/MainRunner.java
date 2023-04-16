package com.violetbeach.cryptoautotrading.runner;

import com.violetbeach.cryptoautotrading.port.in.BuyLowPriceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainRunner implements CommandLineRunner {

    private final BuyLowPriceUseCase buyLowPriceUseCase;

    private static final Double rowPriceRatio = 0.95;
    private static final Integer rowPricePerMonths = 5;


    public void run(String... args) {
        while(true) {
            buyLowPriceUseCase.execute(rowPriceRatio, rowPricePerMonths);
        }
    }
}
