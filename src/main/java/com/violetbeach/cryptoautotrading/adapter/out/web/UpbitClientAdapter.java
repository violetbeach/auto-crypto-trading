package com.violetbeach.cryptoautotrading.adapter.out.web;

import com.violetbeach.cryptoautotrading.adapter.out.web.feign.UpbitFeignClient;
import com.violetbeach.cryptoautotrading.port.in.GetTwoMonthPriceUseCase;
import com.violetbeach.cryptoautotrading.port.in.GetKrwMarketsUseCase;
import com.violetbeach.cryptoautotrading.service.domain.MarketInfo;
import com.violetbeach.cryptoautotrading.service.domain.TwoMonthCandleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpbitClientAdapter implements GetKrwMarketsUseCase, GetTwoMonthPriceUseCase {

    private final UpbitFeignClient upbitFeignClient;

    public List<MarketInfo> getAllMarkets() {
        List<GetMarketResponse> marketResponses = upbitFeignClient.getMarkets();

        List<MarketInfo> markets = marketResponses.stream()
                .filter(response -> response.market().contains("KRW"))
                .filter(response -> response.marketWarning().equals(MarketStatus.NONE.name()))
                .map(response -> new MarketInfo(
                        response.market(),
                        response.koreanName(),
                        response.englishName(),
                        !response.marketWarning().equals(MarketStatus.NONE.name())
                )).toList();
        return markets;
    }

    public TwoMonthCandleInfo getTwoMonthCandleInfo(String market) {
        List<GetCandleResponse> candlePerMonth = upbitFeignClient.getCandlePerMonth(market, 2);

        TwoMonthCandleInfo twoMonthCandleInfo = new TwoMonthCandleInfo(
                market,
                Math.min(candlePerMonth.get(0).lowPrice(), candlePerMonth.get(1).lowPrice()),
                candlePerMonth.get(0).tradePrice()
        );
        return twoMonthCandleInfo;
    }
}
