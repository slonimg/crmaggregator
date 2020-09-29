package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class SupportCase {

    @JsonProperty("CaseID")
    int caseId;

    @JsonProperty("PRODUCT_NAME")
    String productName;

    @JsonProperty("CREATED_ERROR_CODE")
    int errorCode;

    @JsonProperty("Provider")
    String provider;

    @JsonProperty("CustomerID")
    int customerId;

    @JsonProperty("STATUS")
    String status;

    @JsonProperty("TICKET_CREATION_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyyH:mm")
    Date creationTime;

    @JsonProperty("LAST_MODIFIED_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyyH:mm")
    @JsonDeserialize()
    Date updateTime;
}

