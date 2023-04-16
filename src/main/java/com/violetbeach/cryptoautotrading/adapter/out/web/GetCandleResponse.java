package com.violetbeach.cryptoautotrading.adapter.out.web;

import com.fasterxml.jackson.annotation.JsonProperty;

record GetCandleResponse(
        String market,
        @JsonProperty(value = "low_price")
        Double lowPrice,
        @JsonProperty(value = "trade_price")
        Double tradePrice
){
}
