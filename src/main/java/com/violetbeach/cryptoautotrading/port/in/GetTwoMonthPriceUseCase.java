package com.violetbeach.cryptoautotrading.port.in;

import com.violetbeach.cryptoautotrading.service.domain.TwoMonthCandleInfo;

public interface GetTwoMonthPriceUseCase {

    TwoMonthCandleInfo getTwoMonthCandleInfo(String market);

}
