package com.learning.bank.controller;

import com.learning.bank.dto.FavAccountDto;
import com.learning.bank.model.FavBankAccount;
import com.learning.bank.service.FavBankAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("api/account")
public class FavAccountController {
    @Autowired
    FavBankAccountService favBankAccountService;

    @Operation(summary = "Create a new bank account favourite for a customer by given customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "500", description = "Internal Error.")
    })
    @PostMapping("{customerId}")
    public ResponseEntity<FavBankAccount> addFavouriteBankAccount(@Parameter(description = "The ID of the customer") @PathVariable Long customerId,
                                                            @Parameter(description = "Bank account details of the favourite account") @RequestBody @Valid FavAccountDto favAccountDto) {
        log.info("Fav account controller to get the list of payees for: {}", customerId);
        FavBankAccount favBankAccount = new FavBankAccount();
        BeanUtils.copyProperties(favAccountDto, favBankAccount);
        FavBankAccount savedFavBankAccount = favBankAccountService.createBankAccount(customerId, favBankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFavBankAccount);
    }

}
