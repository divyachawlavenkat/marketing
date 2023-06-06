package com.allica_test.marketing.integrationTest;



import com.allica_test.marketing.model.Customer;
import com.allica_test.marketing.model.MarketingPre;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerCrudIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


@Test
public void saveTest() throws Exception {

    //given
    String requestUrl = "/customer/save";

    //when
    Customer customer = new Customer();
    customer.setFirst_name("Divya");
    customer.setLast_name("Venkatesh");
    customer.setMarket_pre(MarketingPre.EMAIL);

    //then
    mockMvc.perform(post(requestUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title", is("johnTitle")))
            .andExpect(jsonPath("$.description", is("JohnDoe")));
}
}
