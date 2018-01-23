package com.example.strings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author rbenhammane on 1/23/18.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${app.api.title}")
    private String apiTitle;

    @Value("${app.api.description}")
    private String apiDescription;

    @Value("${app.api.version}")
    private String apiVersion;

    @Value("${app.api.termofserviceurl}")
    private String termOfServiceUrl;

    @Value("${app.api.contact.name}")
    private String contactName;

    @Value("${app.api.contact.url}")
    private String contactUrl;

    @Value("${app.api.contact.email}")
    private String contactEmail;

    @Value("${app.api.license}")
    private String license;

    @Value("${app.api.licenseurl}")
    private String licenseUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(handler -> RequestHandlerSelectors.withClassAnnotation(RestController.class).apply(handler))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                apiTitle,
                apiDescription,
                apiVersion,
                termOfServiceUrl,
                new Contact(contactName, contactUrl, contactEmail),
                license,
                licenseUrl,
                Collections.emptyList());
    }
}
