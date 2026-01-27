package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

private final AccountService accountService;
private final MessageService messageService;
private final AccountRepository accountRepository;
@Autowired
    public SocialMediaController(AccountService accountService,
                                 MessageService messageService,
                                 AccountRepository accountRepository) {
     this.accountService = accountService;
        this.messageService = messageService;
        this.accountRepository = accountRepository;
    }
@PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {

    if (account == null || account.getUsername() == null) {
            return ResponseEntity.status(400).build();
        }

    Optional<Account> existing = accountRepository.findByUsername(account.getUsername());
        if (existing.isPresent()) {
            return ResponseEntity.status(409).build();
        }

    Account created = accountService.register(account);
        if (created == null) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.ok(created);
    }
@PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account loggedIn = accountService.login(account);

        if (loggedIn == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(loggedIn);
    }
@PostMapping("/messages")
public ResponseEntity<Message> createMessage(@RequestBody Message message) {
    Message created = messageService.createMessage(message);

 if (created == null) {
        return ResponseEntity.status(400).build();
    }

    return ResponseEntity.ok(created);
}

@GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
    
@GetMapping("/messages/{messageId}")
public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {

    Message message = messageService.getMessageById(messageId);

    if (message == null) {
        return ResponseEntity.ok().build();
    }

    return ResponseEntity.ok(message);
}

@DeleteMapping("/messages/{messageId}")
 public ResponseEntity<?> deleteMessage(@PathVariable Integer messageId) {

    Integer rows = messageService.deleteMessage(messageId);

    if (rows == 0) {
        return ResponseEntity.ok().build();
    }

    return ResponseEntity.ok(rows);
}

@PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer messageId,
                                                 @RequestBody Message body) {

        if (body == null) {
            return ResponseEntity.status(400).build();
        }

        Integer rows = messageService.updateMessageText(messageId, body.getMessageText());

        if (rows == 0) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.ok(rows);
    }
@GetMapping("/accounts/{accountId}/messages")
 public List<Message> getMessagesByUser(@PathVariable Integer accountId) {
    return messageService.getMessagesByAccountId(accountId);
}

}

