package com.fourheads.bookstore.configuracoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fourheads.bookstore.service.DBservice;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	DBservice bservice;

    @Bean
    void instanciaBaseDeDados() {
        this.bservice.instanciaBaseDeDados();
    }
}
