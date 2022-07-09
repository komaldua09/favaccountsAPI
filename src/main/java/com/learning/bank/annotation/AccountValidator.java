package com.learning.bank.annotation;

import com.learning.bank.dto.FavAccountDto;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountValidator implements ConstraintValidator<AccountIsValid, FavAccountDto> {

    @Override
    public void initialize(AccountIsValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(FavAccountDto favAccountDto, ConstraintValidatorContext context) {
        if (favAccountDto == null || StringUtils.isEmpty(favAccountDto.getAccountName()) || StringUtils.isEmpty(favAccountDto.getIban()))
            return false;

        if (favAccountDto.getIban().length() > 20) return false;

        return true;
    }
}
