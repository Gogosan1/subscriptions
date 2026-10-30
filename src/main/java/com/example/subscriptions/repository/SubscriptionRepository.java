package com.example.subscriptions.repository;

import com.example.subscriptions.model.entity.Subscription;
import com.example.subscriptions.model.enums.SubscriptionType;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    @EntityGraph(attributePaths = "user")
    boolean existsByUser_LoginAndSubscriptionTypeAndExpiredDateTimeAfter(
            String login,
            SubscriptionType subscriptionType,
            OffsetDateTime now
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @EntityGraph(attributePaths = "user")
    List<Subscription> findBySubscriptionTypeAndExpiredDateTimeBefore(
            SubscriptionType subscriptionType,
            OffsetDateTime now
    );
}