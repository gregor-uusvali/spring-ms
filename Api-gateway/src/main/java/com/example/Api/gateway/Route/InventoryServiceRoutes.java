package com.example.Api.gateway.Route;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class InventoryServiceRoutes {

  @Bean
  public RouterFunction<ServerResponse> inventoryServiceRouter() {
    return GatewayRouterFunctions.route("inventory-service")
        .route(RequestPredicates.GET("/api/v1/inventory/venue/{venueId}"),
            request -> forwardWithPathvariable(request, "venueId", "http://localhost:8080/api/v1/inventory/venue/"))
        .route(RequestPredicates.GET("/api/v1/inventory/events/{eventId}"),
            request -> forwardWithPathvariable(request, "eventId", "http://localhost:8080/api/v1/inventory/events/"))
        .build();
  }

  private ServerResponse forwardWithPathvariable(ServerRequest request, String pathVariable, String url) throws Exception {
    String venueId = request.pathVariable(pathVariable);
    return HandlerFunctions.http(url + venueId).handle(request);
  }
}
