package com.violetbeach.cryptoautotrading.service;

import com.violetbeach.cryptoautotrading.port.in.GetKrwMarketsUseCase;
import com.violetbeach.cryptoautotrading.service.domain.MarketInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainScheduler {

    private final GetKrwMarketsUseCase getAllMarketsUseCase;

    @Scheduled(cron = "*/5 * * * * *")
    public void execute() {
        List<MarketInfo> targetMarsets = getAllMarketsUseCase.getAllMarkets();
        Set<String> marketSet = targetMarsets
                .stream()
                .map(MarketInfo::getMarket)
                .collect(Collectors.toSet());
    }
}
