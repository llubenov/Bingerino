package com.cognizant.bingo.account.controller;

import com.cognizant.bingo.account.domain.Account;
import com.cognizant.bingo.account.domain.Prize;
import com.cognizant.bingo.account.domain.Ticket;
import com.cognizant.bingo.account.service.IAccountService;
import com.cognizant.bingo.account.util.AccountURLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.ticket}")
    private String ticketURL;
    @Value("${url.prize}")
    private String prizeURL;
    @Value("${baseURL}")
    private String baseURL;

    @RequestMapping(value = "ticket", method = RequestMethod.GET)
    public void send() {
        Ticket ticket = restTemplate.getForObject(ticketURL + "/random", Ticket.class);
        Prize prize = restTemplate.getForObject(prizeURL + "/prize" + ticket.getTicketNumber(), Prize.class);

        System.out.println(ticket.getTicketNumber());
    }

    @RequestMapping(value = "accounts", method = RequestMethod.GET)
    public List<Account> list() {
        return accountService.getAllAccounts();
    }

    @GetMapping(AccountURLS.URL_GET_ACCOUNTS)
    public List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping(AccountURLS.URL_GET_ACCOUNT_BY_ID)
    public Account getAccount(@PathVariable Long id) {
        return accountService.retrieveAccount(id);
    }

    @DeleteMapping(AccountURLS.URL_DELETE_ACCOUNT)
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }

    @PutMapping(AccountURLS.URL_UPDATE_ACCOUNT)
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable Long id) {
        return accountService.updateAccount(id, account);
    }

    @PostMapping(AccountURLS.URL_CREATE_ACCOUNT)
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
        String accNumberGen = restTemplate.getForObject(baseURL + "acc_num_gen", String.class);
        Integer genPrizeAmount = restTemplate.getForObject(prizeURL + "prize function path" + accNumberGen, Integer.class);

        account.setAccountNumber(accNumberGen);

//        return new object with everyhting;
        return null;
    }


}
