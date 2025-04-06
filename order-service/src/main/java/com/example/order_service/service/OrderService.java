package com.example.order_service.service;

import com.example.order_service.dto.InventoryResponse;
import com.example.order_service.dto.OrderRequest;
import com.example.order_service.mapper.OrderLineItemsMapper;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderLineItems;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDtoList().stream()
                .map(OrderLineItemsMapper::mapToDto).toList();

        order.setOrderLineItems(orderLineItems);

         List<String> skuCodes=order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

        //Invokes inventory service and if product is in stock place order
         InventoryResponse[] inventoryResponses=webClient.get()
                                 .uri("http://localhost:8082/api/inventory",
                                         uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                                 .retrieve()
                                 .bodyToMono(InventoryResponse[].class)
                                 .block();


        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

         if (allProductsInStock){
             orderRepository.save(order);
         }else{
             throw new IllegalArgumentException("No product in stock");
         }

    }


}
