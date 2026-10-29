package com.example.subscriptions.service;

import com.example.subscriptions.model.entity.Subscription;
import com.example.subscriptions.model.entity.User;
import com.example.subscriptions.model.enums.SubscriptionType;
import com.example.subscriptions.repository.SubscriptionRepository;
import com.example.subscriptions.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public SubscriptionType getSubscriptionTypeInfo(String userLogin) {
        User user = userRepository.findById(userLogin)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Пользователь с таким login:" + userLogin + " не зарегистрирован в ситеме!"));

        boolean hasPaidSubscription =
                subscriptionRepository.existsByUser_LoginAndSubscriptionTypeAndExpiredDateTimeAfter(
                        userLogin,
                        SubscriptionType.PAID,
                        OffsetDateTime.now());

        return hasPaidSubscription ? SubscriptionType.PAID : SubscriptionType.FREE;
    }

    @Transactional
    public List<User> deleteExpiredSubscriptions() {
        List<Subscription> expired = subscriptionRepository.findBySubscriptionTypeAndExpiredDateTimeBefore(
                SubscriptionType.PAID,
                OffsetDateTime.now());
        List<User> usersWithExpiredSubscription = expired.stream()
                .map(Subscription::getUser)
                .toList();

        subscriptionRepository.deleteAll(expired);
        return usersWithExpiredSubscription;
    }

}
