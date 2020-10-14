package com.example.demo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AggregatorApiImpl implements AggregatorApi {

    CrmConsumer bananaCrm;
    CrmConsumer strawberryCrm;
    AggregationManager aggregationManager;

    @Override
    public List<SupportCase> get(String product) {
        List<SupportCase> result = new ArrayList<>();

        aggregationManager.refreshData();
        for (SupportCase supportCase : aggregationManager.getSupportCases().values()){
            if (!product.isEmpty()) {
                if (Objects.equals(supportCase.getProductName(), product)) {
                    result.add(supportCase);
                }
        } else {
            result.add(supportCase);
        }
    }
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
