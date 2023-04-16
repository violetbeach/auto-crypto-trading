package com.violetbeach.cryptoautotrading.port.output;

import com.violetbeach.cryptoautotrading.application.domain.MarketInfo;

import java.util.List;

public interface LoadSafetyKrwMarketsPort {
    List<MarketInfo> getMarkets();
}
