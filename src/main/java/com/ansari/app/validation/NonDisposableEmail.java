package com.ansari.app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER}) // where it can be used
@Retention(RetentionPolicy.RUNTIME)  // when it will be executed
@Constraint(validatedBy = EmailDomainValidator.class) // specify the validator class
public @interface NonDisposableEmail {

    String message() default "Disposable email addresses are not allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
