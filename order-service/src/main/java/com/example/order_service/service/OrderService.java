package com.example.order_service.service;

import com.example.order_service.dto.InventoryResponse;
import com.example.order_service.dto.OrderRequest;
import com.example.order_service.mapper.OrderLineItemsMapper;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderLineItems;
import com.example.order_service.repository.OrderRepository;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.parsers.SAXParser;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;

    public String placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDtoList().stream()
                .map(OrderLineItemsMapper::mapToDto).toList();

        order.setOrderLineItems(orderLineItems);

         List<String> skuCodes=order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

         log.info("Calling inventory service");

         Span inventoryServiceLookup=tracer.nextSpan().name("InventoryServiceLookup");

         try(Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceLookup.start())){

             //Invokes inventory service and if product is in stock place order
             InventoryResponse[] inventoryResponses=webClientBuilder.build().get()
                     .uri("http://inventory-service/api/inventory",
                             uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                     .retrieve()
                     .bodyToMono(InventoryResponse[].class)
                     .block();


             boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

             if (allProductsInStock){
                 orderRepository.save(order);
                 return "Order placed successfully";
             }else{
                 throw new IllegalArgumentException("No product in stock");
             }

         }finally {
             inventoryServiceLookup.end();
         }

    }


}
