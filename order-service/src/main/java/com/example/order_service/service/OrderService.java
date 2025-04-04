package com.example.order_service.service;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.mapper.OrderLineItemsMapper;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderLineItems;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDtoList().stream()
                .map(OrderLineItemsMapper::mapToDto).toList();

        order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);
    }


}
