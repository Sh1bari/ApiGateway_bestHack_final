package com.example.zuulproxy;

import lombok.*;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@org.springframework.context.annotation.Configuration
public class Configuration{
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("spring_boot_eureka", r -> r.path("/eureka-ui/**")
                        .uri("lb://EUREKA-SERVER/"))
                .route("spring_boot_eureka", r -> r.path("/eureka/**")
                        .uri("lb://EUREKA-SERVER/"))
                .route("spring_boot_admin", r -> r.path("/admin-ui/**")
                        .uri("lb://MAIN-SERVER/admin-ui"))

                //Auth server swagger
                .route("auth_server_swagger1", r -> r.path("/api/auth/swagger-ui/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://AUTH-SERVER/"))
                .route("auth_server_swagger2", r -> r.path("/api/auth/api/auth/v3/api-docs/swagger-config")
                        .filters(f -> f.rewritePath("/api/auth/api/auth/v3/api-docs/swagger-config", "/api/auth/v3/api-docs/swagger-config"))
                        .uri("lb://AUTH-SERVER/"))
                .route("auth_server_swagger3", r -> r.path("/api/auth/api/auth/v3/api-docs")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://AUTH-SERVER/api/auth/v3/api-docs"))

                //Auth server
                .route("auth_server", r -> r.path("/api/auth/**")
                        .uri("lb://AUTH-SERVER/api/auth/"))

                //Main server swagger
                .route("main_server_swagger1", r -> r.path("/api/main/swagger-ui/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://MAIN-SERVER/"))
                .route("main_server_swagger2", r -> r.path("/api/main/api/main/v3/api-docs/swagger-config")
                        .filters(f -> f.rewritePath("/api/main/api/main/v3/api-docs/swagger-config", "/api/main/v3/api-docs/swagger-config"))
                        .uri("lb://MAIN-SERVER/"))
                .route("main_server_swagger3", r -> r.path("/api/main/api/main/v3/api-docs")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://MAIN-SERVER/api/main/v3/api-docs"))

                //Main server
                .route("main_server", r -> r.path("/api/main/**")
                        .uri("lb://MAIN-SERVER/api/main/"))
                .build();
    }

}
