package com.fotovaCreation.pricingservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PriceController
{
    List<Price> priceList= new ArrayList<Price>();

    @GetMapping("/price/{productId}")
    public Price getPrice(@PathVariable Long productId)
    {
        populatePriceList();
        Price p = getPriceOfProduct(productId);
        return p;
    }

    private Price getPriceOfProduct(Long id)
    {
        for (Price p: priceList)
        {
            if(p.getProductId() == id)
            {
                return p;
            }
        }

        return null;
    }

    private void populatePriceList()
    {
        Price p1 = new Price(1L,1L, 10,5);
        Price p2 = new Price(2L,2L, 99,50);
        Price p3 = new Price(3L,3L, 150,99);
        Price p4 = new Price(4L,4L, 1000,7000);

        priceList.addAll(Arrays.asList(p1,p2,p3,p4));

    }

}
