package com.example.subscriptions.model.event;

import java.util.List;

public record ExpiredSubscriptionEvent(
        String usersLogin
) {
}
