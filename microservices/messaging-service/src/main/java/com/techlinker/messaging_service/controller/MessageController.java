package com.techlinker.messaging_service.controller;

import com.techlinker.messaging_service.entities.Message;
import com.techlinker.messaging_service.service.IMessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final IMessageService messageService;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message){
        try {
            Message messageCreate = messageService.save(message);
            return new ResponseEntity<>(messageCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllMessages() {
        try {
            return new ResponseEntity<>(messageService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMessageById(@PathVariable("messageId") Long messageId) {
        try {
            return new ResponseEntity<>(messageService.getById(messageId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMessage(@PathVariable("messageId") Long messageId) {
        try {
            messageService.delete(messageId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
