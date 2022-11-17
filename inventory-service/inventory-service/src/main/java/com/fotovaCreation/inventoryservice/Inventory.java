package com.fotovaCreation.inventoryservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory
{
    private Long inventoryId;
    private Long productId;
    private Boolean inStock;

}
