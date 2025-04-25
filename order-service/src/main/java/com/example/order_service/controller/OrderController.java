package com.example.order_service.controller;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.model.Order;
import com.example.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //burdaki name ile app.properties-de ki .instances-den soraki eyni olmalidir
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name="inventory")

    //retry implement eliyenden 500 verir
    @Retry(name="inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
       return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException exception){
        log.info( "Something went wrong, come back after some time");
        return CompletableFuture.supplyAsync(()->"Something went wrong, come back after some time");
    }
}
