package com.violetbeach.cryptoautotrading.adapter.out.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetMarketResponse (
        String market,
        @JsonProperty(value = "korean_name")
        String koreanName,
        @JsonProperty(value = "english_name")
        String englishName,
        @JsonProperty(value = "market_warning")
        String marketWarning
){
}
