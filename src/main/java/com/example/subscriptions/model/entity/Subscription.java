package com.example.subscriptions.model.entity;

import com.example.subscriptions.model.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_login")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SubscriptionType subscriptionType;

    private OffsetDateTime expiredDateTime;


}
