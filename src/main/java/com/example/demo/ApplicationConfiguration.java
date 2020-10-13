package com.example.demo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }

    @Bean
    @ConfigurationProperties("crm.banana")
    public CrmConsumer bananaCrm(){
        return new CrmConsumer(crmConstraintsHandler());
    }

    @Bean
    @ConfigurationProperties("crm.strawberry")
    public CrmConsumer strawberryCrm(){
        return new CrmConsumer(crmConstraintsHandler());
    }

    @Bean
    @ConfigurationProperties("crm.constraints")
    public CrmConstraintsHandler crmConstraintsHandler() { return new CrmConstraintsHandler(); }
}
