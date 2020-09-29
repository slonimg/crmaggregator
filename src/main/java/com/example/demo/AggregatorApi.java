package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/myAggregatorHub")
public interface AggregatorApi {

    @GetMapping(value = "")
    List<SupportCase> get(@RequestParam(name = "product", required = false) String product);
}
