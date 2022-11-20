package com.fotovaCreation.productservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "inventory-client", url = "http://localhost:8003/")
public interface InventoryClient
{
    @GetMapping("/inventory/{productId}")
    public Inventory getProductInventory(@PathVariable("productId") Long productId);
}