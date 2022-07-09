package com.learning.bank.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = AccountValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface AccountIsValid {

    String message() default "{The request payload is invalid}";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
