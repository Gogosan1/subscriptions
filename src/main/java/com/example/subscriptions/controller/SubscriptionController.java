package com.example.subscriptions.controller;

import com.example.subscriptions.model.enums.SubscriptionType;
import com.example.subscriptions.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/info/{userLogin}")
    public ResponseEntity<SubscriptionType> getSubscriptionInfo(@PathVariable String userLogin){
        return ResponseEntity.ok(subscriptionService.getSubscriptionTypeInfo(userLogin));
    }
}
