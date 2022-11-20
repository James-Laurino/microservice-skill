package com.fotovaCreation.inventoryservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class InventoryController
{
    List<Inventory> inventoryList = new ArrayList<Inventory>();

    @GetMapping("/inventory/{productId}")
    public Inventory getProductInventory(@PathVariable Long productId)
    {
        populateInventoryList();
        Inventory inventory = inventoryList.get(Math.toIntExact(productId));
        return inventory;
    }

    public Inventory getInventoryById(Long id)
    {
        for (Inventory i: inventoryList)
        {
            if(i.getProductId() == id)
            {
                return i;
            }
        }

        return null;
    }

    public void populateInventoryList()
    {
        Inventory i1 = new Inventory(1L,1L,true);
        Inventory i2 = new Inventory(2L,2L,true);
        Inventory i3 = new Inventory(3L,3L,false);
        Inventory i4 = new Inventory(4L,4L,true);

        inventoryList.addAll(Arrays.asList(i1,i2,i3,i4));
    }
}
