package com.kawa.products.productsapi.message;

import com.kawa.products.productsapi.domain.service.product.ProductService;
import com.kawa.products.productsapi.dto.OrderMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderMessageConsumerTest {

    @Mock
    private ProductService mockProductService;

    private OrderMessageConsumer orderMessageConsumerUnderTest;

    @BeforeEach
    void setUp() {
        orderMessageConsumerUnderTest = new OrderMessageConsumer(mockProductService);
    }

    @Test
    void testConsumeMessage() {
        // GIVEN
        final OrderMessageDTO orderMessageDTO = new OrderMessageDTO(List.of(0L));

        // WHEN
        orderMessageConsumerUnderTest.consumeMessage(orderMessageDTO);

        // THEN
        verify(mockProductService).reduceStock(new OrderMessageDTO(List.of(0L)));
    }
}
