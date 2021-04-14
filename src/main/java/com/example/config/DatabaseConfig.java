package com.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.TemplateProjectApplication;


@Configuration
@ComponentScan(basePackageClasses = TemplateProjectApplication.class)
@EntityScan("com.example.domain")
@MapperScan({"com.example.mapper"})
@EnableTransactionManagement
public class DatabaseConfig {
}
