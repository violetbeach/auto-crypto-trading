package com.violetbeach.cryptoautotrading.port.output;

import com.violetbeach.cryptoautotrading.application.domain.CandleInfoPerMonths;

public interface LoadCandlesPort {
    CandleInfoPerMonths getCandleInfoPerMonths(String market, Integer perMonths);
}
