package com.violetbeach.cryptoautotrading.adapter.out.web;

import com.violetbeach.cryptoautotrading.application.domain.CandleInfoPerMonths;
import com.violetbeach.cryptoautotrading.application.domain.MarketInfo;
import com.violetbeach.cryptoautotrading.port.output.LoadSafetyKrwMarketsPort;
import com.violetbeach.cryptoautotrading.port.output.LoadCandlesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
class UpbitClientAdapter implements LoadSafetyKrwMarketsPort, LoadCandlesPort {

    private final UpbitFeignClient upbitFeignClient;

    public List<MarketInfo> getMarkets() {
        List<GetMarketResponse> marketResponses = upbitFeignClient.getMarkets();

        List<MarketInfo> markets = marketResponses.stream()
                .filter(response -> response.market().contains("KRW"))
                .filter(response -> response.marketWarning().equals(MarketStatus.NONE.name()))
                .map(response -> new MarketInfo(
                        response.market(),
                        response.koreanName(),
                        response.englishName(),
                        false
                )).toList();
        return markets;
    }

    public CandleInfoPerMonths getCandleInfoPerMonths(String market, Integer perMonths) {
        List<GetCandleResponse> response = upbitFeignClient.getCandlePerMonth(market, perMonths);

        Double lowPrice = response.stream()
                .mapToDouble(GetCandleResponse::lowPrice)
                .min()
                .orElseThrow(NoSuchElementException::new);

        CandleInfoPerMonths candlePerMonths = new CandleInfoPerMonths(
                market,
                lowPrice,
                response.get(0).tradePrice()
        );

        return candlePerMonths;
    }
}
