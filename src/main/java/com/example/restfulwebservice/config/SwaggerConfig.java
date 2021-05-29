package com.example.restfulwebservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static Contact DEFAULT_CONTACT = new  Contact("mtJu","https://withsion.or.kr","with6928@gmail.com");


    private static final ApiInfo  DEFAULT_API_INFO = new ApiInfo("Awesom API Tittle","My user management RestApi service"
    , "1.0","urn:tos",DEFAULT_CONTACT,"Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0"
    ,new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            Arrays.asList("application/json", "application/xml"));


//
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(DEFAULT_API_INFO)
//                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
//                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
//
//    }

    @Bean
    public LinkDiscoverers LinkDiscoverers(){
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("SwaggerConfig")
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo buildApiInfo() {
        Contact contact = new Contact("mtJu", "https://withsion.or.kr", "with6928@gmail.com");
        return new ApiInfoBuilder()
                .title("SwaggerConfig")
                .description("API Description")
                .license("license")
                .version("1.0")
                .contact(contact)
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}



