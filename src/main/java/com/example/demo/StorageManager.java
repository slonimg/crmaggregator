package com.example.demo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StorageManager {

    // conifguration
    CrmConsumer bananaCrm;
    CrmConsumer strawberryCrm;
    CrmConstraintsHandler crmConstraintsHandler;

    // local
    Map<Integer, SupportCase> supportCases;
    List<CrmConsumer> consumers;

    public StorageManager(@Qualifier("bananaCrm") CrmConsumer bananaCrm,
                          @Qualifier("strawberryCrmmm") CrmConsumer strawberryCrm,
                          CrmConstraintsHandler crmConstraintsHandler) {
        this.bananaCrm = bananaCrm;
        this.strawberryCrm = strawberryCrm;
        this.crmConstraintsHandler = crmConstraintsHandler;

        consumers = new ArrayList<>();
        consumers.add(bananaCrm);
        consumers.add(strawberryCrm);

        supportCases = new HashMap<>();
    }

    public void refreshData() {
        // TODO: We need to get an indication on deleted cases - we will assume that data is only accumulated
        for (CrmConsumer consumer : consumers) {
            if (crmConstraintsHandler.canExecute(consumer.getProducts(), consumer.getLastRefresh())) {
                List<SupportCase> supportCasesData = consumer.getData();

                // TODO: replace with volatile DB Storage / redis to cache aggregations
                for (SupportCase supportCase : supportCasesData) {
                    supportCases.put(supportCase.getCaseId(), supportCase);
                }
            }
        }
    }
}
