package com.example.testpool.controller;

import com.example.testpool.service.AsyncService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AsyncTestController {


    @Autowired
    private AsyncService asyncService;

    @GetMapping("/testAsync")
    public String async() throws Exception{
        asyncService.executeAsync();
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("code", 200);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(respMap);
    }

}
