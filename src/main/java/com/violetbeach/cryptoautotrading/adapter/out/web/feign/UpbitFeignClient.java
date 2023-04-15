package com.violetbeach.cryptoautotrading.adapter.out.web.feign;

import com.violetbeach.cryptoautotrading.adapter.out.web.GetCandleResponse;
import com.violetbeach.cryptoautotrading.adapter.out.web.GetMarketResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "upbit-client", url = "https://api.upbit.com/v1")
public interface UpbitFeignClient {

    @GetMapping("/market/all?isDetails=true")
    List<GetMarketResponse> getMarkets();

    @GetMapping("/candles/months")
    List<GetCandleResponse> getCandlePerMonth(
            @RequestParam("market") String market,
            @RequestParam("count") Integer count);

}
