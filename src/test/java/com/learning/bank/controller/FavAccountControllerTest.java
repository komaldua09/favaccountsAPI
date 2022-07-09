package com.learning.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.bank.dto.FavAccountDto;
import com.learning.bank.model.APIError;
import com.learning.bank.model.Bank;
import com.learning.bank.model.Customer;
import com.learning.bank.model.FavBankAccount;
import com.learning.bank.repository.BankRepository;
import com.learning.bank.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class FavAccountControllerTest {

    @InjectMocks
    FavAccountController favAccountController;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BankRepository bankRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Before
    @Transactional
    public void setup(){
        insertData();

    }
    @Test
    public void testAddFavouriteBankAccountNegative1() throws Exception {
        FavAccountDto favAccountDto = FavAccountDto.builder()
                .accountName("Account1")
                .iban("ABCD123400000000")
                .build();
        MvcResult mvcResult = mockMvc.perform(post(String.format("%s/%s", "/api/account", "2"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(favAccountDto)))
                .andDo(print())
                .andExpect(status().isPreconditionFailed()).andReturn();
        APIError apiError = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), APIError.class);
        Assert.assertEquals(apiError.getMessage(), "Customer not found with the given Id.");
    }

    @Test
    public void testAddFavouriteBankAccountNegative2() throws Exception {
        FavAccountDto favAccountDto = FavAccountDto.builder()
                .accountName("Account1")
                .iban("ABCD123600000000")
                .build();
        MvcResult mvcResult = mockMvc.perform(post(String.format("%s/%s", "/api/account", "1"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(favAccountDto)))
                .andDo(print())
                .andExpect(status().isPreconditionFailed()).andReturn();

        APIError apiError = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), APIError.class);
        Assert.assertEquals(apiError.getMessage(), "Bank not found with the given IBAN.");
    }

    @Test
    @Transactional
    public void testAddFavouriteBankAccountPositive() throws Exception {
        FavAccountDto favAccountDto = FavAccountDto.builder()
                .accountName("Account1")
                .iban("ABCD123400000000")
                .build();
        mockMvc.perform(post(String.format("%s/%s", "/api/account", "1"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(favAccountDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    private void insertData() {
        bankRepository.save(Bank.builder().code(1234L).bankName("Nairobi bank").build());
        customerRepository.save(Customer.builder().customerId(1L).customeName("Komal").build());
    }


}
