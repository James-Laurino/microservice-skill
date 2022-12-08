package com.fotovacreation.gateway;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyCustomFilter extends AbstractGatewayFilterFactory<MyCustomFilter.Config> {
    public MyCustomFilter() {
        super(Config.class);
    }

    private boolean isAuthorizationValid(String header) {
        // Code to check auth
        return true;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        return response.setComplete();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> { // exchange recupère la request et la modifier & chain pour send back response
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey("Authorization")) {
                return this.onError(exchange, "No Authorization header found", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get("Authorization").get(0);

            if (!this.isAuthorizationValid(authorizationHeader)) {
                return this.onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
            }

            // Ici on change le path de la request mais on fait ce qu'on veut
            // ServerHttpRequest modifiedRequest = exchange.getRequest().mutate().path("/price/103").build();

            //Pre-Filter
            //return chain.filter(exchange.mutate().request(modifiedRequest).build());

            //Post-Filter

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("Post filter"); }));


        };
    }

    public static class Config {
        // Config properties qui va être injectée dans la route de la gateway
    }
}
