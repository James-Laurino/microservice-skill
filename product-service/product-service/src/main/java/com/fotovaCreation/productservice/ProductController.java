package com.fotovaCreation.productservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ProductController
{
    List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();

    public WebClient webClient = WebClient.create();

    @GetMapping("/product/details/{productId}")
    public Mono<Product> getProduct(@PathVariable int productId)
    {
        log.info("je rentre dans le get Product controller");
        populateProductInfo();

        Mono<ProductInfo> productInfo = Mono.just(productInfoList.get(productId));
        Mono<Price> price = webClient.get().uri("http://localhost:8002/price/{productId}", productId).retrieve().bodyToMono(Price.class);
        Mono<Inventory> inventory = webClient.get().uri("http://localhost:8003/inventory/{productId}", productId).retrieve().bodyToMono(Inventory.class);

        log.info("je suis dans le controller et je suis afficher même si la request n'est pas finie ");
//        return Mono.zip(productInfo,price,inventory).map(this::buildProduct);
        return Mono.zip(productInfo,price,inventory).map(tuple ->
            new Product(tuple.getT1().getProductId(), tuple.getT1().getProductName(), tuple.getT1().getProductDesc()
                    , tuple.getT2().getDiscountedPrice(), tuple.getT3().getInStock())
        );
    }

    public Product buildProduct(Tuple3<ProductInfo,Price,Inventory> tuple)
    {
        return new Product(tuple.getT1().getProductId(), tuple.getT1().getProductName(), tuple.getT1().getProductDesc()
        , tuple.getT2().getDiscountedPrice(),tuple.getT3().getInStock());
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
