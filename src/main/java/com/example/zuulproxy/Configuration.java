package com.example.zuulproxy;

import lombok.*;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration{
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("spring_boot_eureka", r -> r.path("/eureka-ui/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://EUREKA-SERVER/"))
                .route("spring_boot_eureka", r -> r.path("/eureka/**")
                        .uri("lb://EUREKA-SERVER/"))
                .route("spring_boot_admin", r -> r.path("/admin-ui/**")
                        .uri("lb://MAIN-SERVER/admin-ui"))
                .build();
    }

}
