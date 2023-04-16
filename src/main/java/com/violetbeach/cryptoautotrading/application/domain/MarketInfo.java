package com.violetbeach.cryptoautotrading.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MarketInfo {
    private final String market;
    private final String koreanName;
    private final String englishName;
    private final Boolean isWarning;
}
