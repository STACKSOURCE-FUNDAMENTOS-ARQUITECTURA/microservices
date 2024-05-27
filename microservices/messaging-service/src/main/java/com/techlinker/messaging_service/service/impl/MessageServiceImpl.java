package com.techlinker.messaging_service.service.impl;
import com.techlinker.messaging_service.entities.Message;
import com.techlinker.messaging_service.feignclient.userservice.UserFeignClient;
import com.techlinker.messaging_service.repository.IMessageRepository;
import com.techlinker.messaging_service.service.IMessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements IMessageService {
    private final IMessageRepository _messageRepository;
    private final UserFeignClient _userClient;

    public MessageServiceImpl(IMessageRepository messageRepository, UserFeignClient userClient) {
        _messageRepository = messageRepository;
        _userClient = userClient;
    }

    @Override
    public Message save(Message message) throws Exception {
        //validar si los usuarios existen cuando se implemente el get id por user
//        UserDTO emitter = _userClient.getUserById(message.getEmitterId()).getBody();
//        UserDTO receiver = _userClient.getUserById(message.getReceiverId()).getBody();
//        if(emitter == null || receiver == null){
//            throw new Exception("Emitter or receiver not found");
//        }

        return _messageRepository.save(message);
    }

    @Override
    public void delete(Long id) throws Exception {
        _messageRepository.deleteById(id);
    }

    @Override
    public List<Message> getAll() throws Exception {
        return _messageRepository.findAll();
    }

    @Override
    public Optional<Message> getById(Long id) throws Exception {
        Message message = _messageRepository.findById(id).orElseThrow();
        return Optional.of(message);
    }
}
