package org.formation;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewaycoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewaycoffeeApplication.class, args);
	}


	@Bean
	RouteLocator coffeeRouteConfig( RouteLocatorBuilder routeBuilder ) {
		return routeBuilder.routes()
				.route(p -> p
						.path("/employeecoffee/**")
						.filters( f -> f.rewritePath("/employeecoffee/(?<segment>.*)", "/${segment}") 
							.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								)
						.uri("lb://DRINKCOFFEEFEIGN")).build();
						
						
						
						
						
	}
	
}
