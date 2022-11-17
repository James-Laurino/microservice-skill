package com.fotovaCreation.productservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo
{
    private Long productId;
    private String productDesc;
    private String productName;
}
