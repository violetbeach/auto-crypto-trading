package com.violetbeach.cryptoautotrading.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TwoMonthCandleInfo {
    private final String market;
    private final Double LowPrice;
    private final Double currentPrice;
}
