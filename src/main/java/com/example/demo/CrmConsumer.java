package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.time.Instant;
import java.util.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrmConsumer {
    // Configurations
    boolean hasArrayWrapper;
    URI endpoint;
    Set<String> products;

    // local
    Instant lastRefresh;
    WebClient client = WebClient.builder()
            .codecs(configurer -> configurer
                    .defaultCodecs()
                    .maxInMemorySize(50 * 1024 * 1024)
            ).build();
    ObjectMapper mapper = new ObjectMapper();

    public List<SupportCase> getData() {
        WebClient.ResponseSpec responseSpec = client
                .get()
                .uri(endpoint)
                .retrieve();

        Object[] errorsRaw = responseSpec
                .bodyToMono(Object[].class)
                .block();

        Object errorsList;
        if (hasArrayWrapper) {
            errorsList = errorsRaw != null ? errorsRaw[0] : null;
        } else {
            errorsList = errorsRaw;
        }

        // updating last refresh time to after the refresh succeeded
        // this means that failed requests will not count as data refresh and it may be problematic
        // TODO: consult if this is valid in respect to the crm constraints
        lastRefresh = Instant.now();

        return mapper.convertValue(errorsList, new TypeReference<List<SupportCase>>() { });
    }
}
