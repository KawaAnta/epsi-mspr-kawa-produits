package com.kawa.products.productsapi.message;

import com.kawa.products.productsapi.config.rabbit.RabbitMQConfig;
import com.kawa.products.productsapi.domain.service.product.ProductService;
import com.kawa.products.productsapi.dto.OrderMessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderMessageConsumer {
    private final ProductService productService;

    public OrderMessageConsumer(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeMessage(OrderMessageDTO orderMessageDTO) {
        productService.reduceStock(orderMessageDTO);
    }
}
