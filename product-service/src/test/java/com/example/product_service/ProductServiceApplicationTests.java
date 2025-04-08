package com.example.product_service;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.math.BigInteger;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Slf4j
class ProductServiceApplicationTests {
//	@Container
//	static MongoDBContainer mongoDBContainer= new MongoDBContainer("mongo:4.4.4");
//
//	@Autowired
//	private MockMvc mockMvc;
//    @Autowired
//	private ObjectMapper objectMapper;
//	@Autowired
//	private ProductRepository productRepository;
//
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry registry) {
//		registry.add("spring.data.mongodb.host", mongoDBContainer::getReplicaSetUrl);
//	}

//	@Test
//	void shouldCreateProduct() throws Exception {
////		int count=productRepository.findAll().size();
////		ProductRequest productRequest=getProductRequest();
////		String productRequestString=objectMapper.writeValueAsString(productRequest);
////		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
////				        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
////				        .content(productRequestString))
////				.andExpect(status().isCreated());
////        Assertions.assertEquals(++count, productRepository.findAll().size());
//	}
//
//	@Test
//	void shouldGetProduct() throws Exception {
////		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
////				        .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
////				.andExpect(status().isOk());
////		log.info("Getting done successfully");
//
//	}
//
//	private ProductRequest getProductRequest() {
//		return ProductRequest.builder()
//				.name("iPhone 13")
//				.description("iPhone 13")
//				.price(BigInteger.valueOf(1200))
//				.build();
//	}

}
