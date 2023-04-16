package com.violetbeach.cryptoautotrading.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CandleInfoPerMonths {
    private final String market;
    private final Double LowPrice;
    private final Double currentPrice;
}
