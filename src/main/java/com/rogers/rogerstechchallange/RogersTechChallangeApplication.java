package com.rogers.rogerstechchallange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.server.reactive.HttpHandler;
import reactor.netty.http.server.HttpServer;

@SpringBootApplication
public class RogersTechChallangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RogersTechChallangeApplication.class, args);
	}

}
