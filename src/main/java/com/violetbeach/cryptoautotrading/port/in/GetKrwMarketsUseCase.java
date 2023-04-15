package com.violetbeach.cryptoautotrading.port.in;

import com.violetbeach.cryptoautotrading.service.domain.MarketInfo;

import java.util.List;

public interface GetKrwMarketsUseCase {

    List<MarketInfo> getAllMarkets();

}
