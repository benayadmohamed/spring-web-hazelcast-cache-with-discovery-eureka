package com;


import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    static int cn = 0;
    @Autowired
    @Qualifier("messages")
    IMap<Integer, String> messages;

    @Cacheable(value = "messages")
    @GetMapping("put")
    public String put() {
        cn += 1;
        messages.put(cn, "message" + cn);
        System.out.println(" ajout " + cn);
        return "put : message " + cn;
    }

    @GetMapping("get")
    public String get() {
        return "get : " + messages.get(cn);
    }
}
