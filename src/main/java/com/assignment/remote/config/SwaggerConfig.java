package com.assignment.remote.config;


import com.assignment.remote.utils.UrlConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;

@Configuration
public class SwaggerConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    @Value("${swagger.isActive}")
    private Boolean isActiveSwagger;
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.version}")
    private String version;


    @PostConstruct
    private void printSwaggerUrl() {
        if (isActiveSwagger) {
            LOGGER.warn("YOUR SWAGGER URL IS : http://yourIp:YourPort/swagger-ui/index.html");
        } else {
            LOGGER.warn("YOUR SWAGGER IS disable to active swagger you should make [swagger.isActive=true] from property file.");
        }
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .enable(isActiveSwagger)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex(UrlConstraint.ROOT + ".*"))
                .build();
    }

    private ApiInfo getApiInfo() {

        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .build();
    }
}
