package com.example.paymentgateway.controller;

import com.example.paymentgateway.service.AccountService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    record  ResquestAccount (String email,double amount){}
    record ResponseAccount (String email,double amount){}
    record RequestTransferAccount(@JsonProperty("from_email") String fromEmail,
                                  @JsonProperty("to_email")String toEmail,
                                  double amount){}
    record ResponseTrasferAccount(String msg){}

    @PostMapping("/transfer")
    public ResponseTrasferAccount transfer(@RequestBody RequestTransferAccount account){
        accountService.transfer(account.fromEmail,
                account.toEmail,
                account.amount);
        return new ResponseTrasferAccount(String.format(
                "%s tranfer $%s amount to %s successfully",
                account.fromEmail,
                account.amount,
                account.toEmail
        ));
    }

    @PostMapping("/withdraw")
    public ResponseAccount withdraw(@RequestBody ResquestAccount resquestAccount){
      double amount = accountService
              .withdraw(resquestAccount.email, resquestAccount.amount);
      return new ResponseAccount(resquestAccount.email, amount);
    }

    @PostMapping("/deposit")
    public ResponseAccount deposit(@RequestBody ResquestAccount resquestAccount){
        double amount = accountService
                .deposit(resquestAccount.email,resquestAccount.amount);
        return new ResponseAccount(resquestAccount.email,amount);
    }

}
