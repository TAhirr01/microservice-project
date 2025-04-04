package com.example.order_service.mapper;

import com.example.order_service.dto.OrderLineItemsDto;
import com.example.order_service.model.OrderLineItems;

public class OrderLineItemsMapper {

    public static OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
