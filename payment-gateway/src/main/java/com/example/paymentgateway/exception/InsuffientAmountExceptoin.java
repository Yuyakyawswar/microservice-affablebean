package com.example.paymentgateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InsuffientAmountExceptoin extends ResponseStatusException {

    public InsuffientAmountExceptoin() {
        super(HttpStatus.BAD_REQUEST,"Insuffient Amount");
    }
}
