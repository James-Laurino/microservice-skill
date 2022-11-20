package com.fotovaCreation.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController
{
    List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();

   // @Autowired
   // private RestTemplate restTemplate;

    @Autowired
    private PriceClient priceClient;

    @Autowired
    private  InventoryClient inventoryClient;

    @GetMapping("/product/details/{productId}")
    public Product getProduct(@PathVariable Long productId)
    {
        populateProductInfo();
        Price price = priceClient.getPrice(productId);

        //Price price = restTemplate.getForObject("http://localhost:8002/price/" + productId, Price.class);

        //Inventory inventory = restTemplate.getForObject("http://localhost:8003/inventory/" + productId, Inventory.class);
        Inventory inventory = inventoryClient.getProductInventory(productId);

        Product product = new Product(productInfoList.get(Math.toIntExact(productId)).getProductId(),
                productInfoList.get(Math.toIntExact(productId)).getProductDesc(), productInfoList.get(Math.toIntExact(productId)).getProductName()
                , price.getOriginalPrice(),inventory.getInStock());


        return product;
    }

    private void populateProductInfo()
    {
        ProductInfo p1 = new ProductInfo(1L,"Un beau livre 1", "Critique 1");
        ProductInfo p2 = new ProductInfo(2L,"Un beau livre 2", "Critique 2");
        ProductInfo p3 = new ProductInfo(3L,"Un beau livre 3", "Critique 3");
        ProductInfo p4 = new ProductInfo(4L,"Un beau livre 4", "Critique 4");

        productInfoList.addAll(Arrays.asList(p1,p2,p3,p4));
    }
}
