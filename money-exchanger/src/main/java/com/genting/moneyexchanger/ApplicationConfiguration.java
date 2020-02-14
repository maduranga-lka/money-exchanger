package com.genting.moneyexchanger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

public class ApplicationConfiguration {
    public static final String SETTING_CUSTOM_CONFIGURATION_FOR_MAINSTAY = "Setting custom configuration for Mainstay:";
    public static final String SETTING_CONTEXT_TO = "Setting context to {}";
    public static final String NOTFOUND_HTML = "/notfound.html";
    public static final String FORBIDDEN_HTML = "/forbidden.html";
    Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Value("${server.servlet.contextPath}")
    private String context;

    private Set<ErrorPage> pageHandlers;

    @PostConstruct
    private void init() {
        pageHandlers = new HashSet<ErrorPage>();
        pageHandlers.add(new ErrorPage(HttpStatus.NOT_FOUND, NOTFOUND_HTML));
        pageHandlers.add(new ErrorPage(HttpStatus.FORBIDDEN, FORBIDDEN_HTML));
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        logger.info(SETTING_CUSTOM_CONFIGURATION_FOR_MAINSTAY);
        logger.info(SETTING_CONTEXT_TO, context);
        factory.setContextPath(context);
        factory.setErrorPages(pageHandlers);
        return factory;
    }
}