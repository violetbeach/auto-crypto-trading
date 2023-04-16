package com.violetbeach.cryptoautotrading.port.in;

public interface BuyLowPriceUseCase {
    void execute(Double ratioByLowPrice, Integer byMonths);
}
