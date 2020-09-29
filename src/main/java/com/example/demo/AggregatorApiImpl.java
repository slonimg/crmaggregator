package com.example.demo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AggregatorApiImpl implements AggregatorApi {

    CrmConsumer bananaCrm;
    CrmConsumer strawberryCrm;

    @Override
    public List<SupportCase> get(String product) {
        System.out.println(product);

        SupportCase supportCase = new SupportCase(1, "BLUE", 123, "456", 789, "Open",
                Date.from(Instant.now().minusSeconds(2592000)), Date.from(Instant.now()));
        List<SupportCase> result = new ArrayList<>();
        result.add(supportCase);
        return result;
    }

    @Override
    public List<SupportCase> getBanana() {
        return bananaCrm.getData();
    }

    @Override
    public List<SupportCase> getStrawberry() {
        return strawberryCrm.getData();
    }
}
