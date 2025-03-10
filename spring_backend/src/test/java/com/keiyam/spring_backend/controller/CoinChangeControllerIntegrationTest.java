package com.keiyam.spring_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CoinChangeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
//
//    @Test
//    void testCalculateMinCoins() throws Exception {
//        String requestBody = """
//            {
//                "amount": 10.00,
//                "denominations": [1, 5, 10]
//            }
//            """;
//
//        mockMvc.perform(post("/api/v1/coin-change")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.coins", hasSize(1)))
//                .andExpect(jsonPath("$.coins[0]", is(10.0)));
//    }
}
