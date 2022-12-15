package com.lionani07.helpdesk.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/hazelcast")
public class HazelcastController {

    @Autowired
    private HazelcastInstance hazelcast;

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    @GetMapping
    public String test() {
        Map<Long, String> map = hazelcast.getMap("data");
        map.putIfAbsent(1L, "liorge");
        return map.get(1L);
    }

    @GetMapping("/lock")
    public void lock() {
        IMap<Long, String> map = hazelcast.getMap("map");
        map.put(1L, "Miriam");
        map.put(2L, "Liorge");


        executor.submit(() -> {
            System.out.println("Thread 1");
            if (map.tryLock(2L)) {
                System.out.println("Thread 1 locked");
            }
            else {
                System.out.println("Thread 1 not locked");
            }
        });

        executor.submit(() -> {
            System.out.println("Thread 2");
            try {
                map.forEach((k, v) -> {
                    if (!map.tryLock(k)) {
                        throw new RuntimeException("Thread 2 Not locked " + k);
                    }
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

    }
}
