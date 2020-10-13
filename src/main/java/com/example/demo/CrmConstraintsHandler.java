package com.example.demo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrmConstraintsHandler {
    Map<String, Integer> productMinimalRefreshTime;

    public boolean canExecute(Set<String> products, Instant lastRefresh) {
        long minutesSinceLastRefresh;
        if (lastRefresh != null) {
            minutesSinceLastRefresh = Duration.between(lastRefresh, Instant.now()).toMinutes();
        } else {
            return true;
        }

        int currentTimeConstraint = 0;
        for (String product : products) {
            int currentProductTimeConstraint = 0;
            if (productMinimalRefreshTime.containsKey(product)) {
                currentProductTimeConstraint = productMinimalRefreshTime.get(product);
            }
            if (currentTimeConstraint < currentProductTimeConstraint) {
                currentTimeConstraint = currentProductTimeConstraint;
            }
        }
        return currentTimeConstraint < minutesSinceLastRefresh;
    }
}
