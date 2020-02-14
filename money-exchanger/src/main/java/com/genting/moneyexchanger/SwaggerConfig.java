package com.genting.moneyexchanger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_XML = "application/xml";
    public static final String COM_GENTING_MONEYEXCHANGER = "com.genting.moneyexchanger";
    public static final String PATH_REGEX = "/.*";
    public static final String EXCHANGE_SERVICE = "Exchange Service";
    public static final String MONEY_EXCHANGE_MANAGEMENT_REST_AP_IS = "Money Exchange Management REST APIs";
    public static final String NAME = "Maduranga";
    public static final String CONTACT = "mg.madugamage@gmail.com";
    public static final String STRING = "";
    public static final String HTTP_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML = "http://www.apache.org/licenses/LICENSE-2.0.html";
    public static final String APACHE_2_0 = "Apache 2.0";
    public static final String VERSION = "1.0.0";

    @Bean
    public Docket api() {
        Set<String> stringSet = new HashSet();

        stringSet.add(APPLICATION_JSON);
        stringSet.add(APPLICATION_XML);
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage(COM_GENTING_MONEYEXCHANGER))
                .paths(PathSelectors.regex(PATH_REGEX))
                .build().apiInfo(apiEndPointsInfo()).produces(stringSet);
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title(EXCHANGE_SERVICE)
                .description(MONEY_EXCHANGE_MANAGEMENT_REST_AP_IS)
                .contact(new Contact(NAME, STRING, CONTACT))
                .license(APACHE_2_0)
                .licenseUrl(HTTP_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML)
                .version(VERSION)
                .build();
    }
}
