package com.violetbeach.cryptoautotrading.service;

import com.violetbeach.cryptoautotrading.port.in.GetKrwMarketsUseCase;
import com.violetbeach.cryptoautotrading.port.in.GetTwoMonthPriceUseCase;
import com.violetbeach.cryptoautotrading.service.domain.MarketInfo;
import com.violetbeach.cryptoautotrading.service.domain.TwoMonthCandleInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainRunner implements CommandLineRunner {

    private final GetKrwMarketsUseCase getAllMarketsUseCase;
    private final GetTwoMonthPriceUseCase getLowPriceUseCase;

    public void run(String... args) {
        while(true) {
            List<MarketInfo> targetMarsets = getAllMarketsUseCase.getAllMarkets();
            Set<String> marketSet = targetMarsets
                    .stream()
                    .map(MarketInfo::getMarket)
                    .collect(Collectors.toSet());

            marketSet.forEach(market -> {
                TwoMonthCandleInfo twoMonthCandleInfo = getLowPriceUseCase.getTwoMonthCandleInfo(market);
                double target = twoMonthCandleInfo.getLowPrice() * 0.95;
                if(target > twoMonthCandleInfo.getCurrentPrice()) {
                    log.info("매수: {}", market);
                }
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    log.error("sleep error: " + e.getMessage());
                }
            });
        }
    }
}
