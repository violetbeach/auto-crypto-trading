package com.violetbeach.cryptoautotrading.application.service;

import com.violetbeach.cryptoautotrading.application.domain.CandleInfoPerMonths;
import com.violetbeach.cryptoautotrading.application.domain.MarketInfo;
import com.violetbeach.cryptoautotrading.port.in.BuyLowPriceUseCase;
import com.violetbeach.cryptoautotrading.port.output.LoadCandlesPort;
import com.violetbeach.cryptoautotrading.port.output.LoadSafetyKrwMarketsPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoTradingService implements BuyLowPriceUseCase {

    private final LoadSafetyKrwMarketsPort loadSafetyKrwMarketsPort;
    private final LoadCandlesPort loadCandlesPort;

    public void execute(Double ratioByLowPrice, Integer byMonths) {
        List<MarketInfo> targetMarsets = loadSafetyKrwMarketsPort.getMarkets();
        Set<String> marketSet = targetMarsets
                .stream()
                .map(MarketInfo::getMarket)
                .collect(Collectors.toSet());

        marketSet.forEach(market -> {
            CandleInfoPerMonths twoMonthCandleInfo = loadCandlesPort.getCandleInfoPerMonths(market, byMonths);
            double target = twoMonthCandleInfo.getLowPrice() * ratioByLowPrice;
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
