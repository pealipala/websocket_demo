package com.example.demo.controller;

import com.example.demo.bean.Chat;
import com.example.demo.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @author yechaoze
 * @version 1.0
 * @date 2020/7/26 16:31
 */
@Controller
public class GreetController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Message greet(Message message){
//        return message;
//    }

    @MessageMapping("/hello")
    public void greet(Message message) {
        simpMessagingTemplate.convertAndSend("/topic/greetings",message);
    }

    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat){
        chat.setFrom(principal.getName());
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(),"/queue/chat",chat);
    }


}
