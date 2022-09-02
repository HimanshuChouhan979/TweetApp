package com.tweetapp.controller;

import com.tweetapp.services.ProducerClass;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class ProducerController {

    private final ProducerClass producerClass;

    public ProducerController(ProducerClass producerClass) {
        this.producerClass = producerClass;
    }

    @PostMapping(value = "/publish")
    public String sendMessage(@RequestBody String message){
        this.producerClass.sendMessage(message);
        return "Pbulished successfully";
    }
    @Bean
    public NewTopic adviceTopic(){
        return new NewTopic("user",3,(short)1);
    }
}
