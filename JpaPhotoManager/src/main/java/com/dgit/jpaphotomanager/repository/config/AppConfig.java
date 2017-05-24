package com.dgit.jpaphotomanager.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.dgit.jpaphotomanager.repository")
public class AppConfig {

}
