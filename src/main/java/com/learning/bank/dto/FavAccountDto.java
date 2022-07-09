package com.learning.bank.dto;

import com.learning.bank.annotation.AccountIsValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@AccountIsValid(message="The Requested payload is invalid")
public class FavAccountDto {
    private String accountName;
    private String iban;
}
