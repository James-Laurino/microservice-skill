package com.fotovaCreation.pricingservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price
{
    private Long priceId;
    private Long productId;
    private Integer originalPrice;
    private Integer discountedPrice;
}
