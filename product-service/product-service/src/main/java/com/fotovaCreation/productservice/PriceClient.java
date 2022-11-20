package com.fotovaCreation.productservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "price-client", url = "http://localhost:8002/") // le base path
public interface PriceClient
{
    @GetMapping("/price/{productId}")
    public Price getPrice(@PathVariable("productId") Long productId);
}
