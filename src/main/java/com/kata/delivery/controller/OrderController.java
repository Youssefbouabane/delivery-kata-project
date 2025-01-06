package com.kata.delivery.controller;

import com.kata.delivery.entity.Order;
import com.kata.delivery.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order API", description = "Endpoints for managing orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Create a new order",
            description = "Creates a new order and returns the created order with additional links",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order created successfully", content = @Content(schema = @Schema(implementation = EntityModel.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
            }
    )
    @PostMapping
    public Mono<ResponseEntity<EntityModel<Order>>> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order)
                .map(this::toModel)
                .map(ResponseEntity::ok);
    }

    // Endpoint pour récupérer toutes les commandes
    @Operation(
            summary = "Get all orders",
            description = "Fetches all orders with hypermedia links",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orders fetched successfully", content = @Content(schema = @Schema(implementation = Flux.class)))
            }
    )
    @GetMapping
    public Mono<ResponseEntity<Flux<EntityModel<Order>>>> getAllOrders() {
        Flux<EntityModel<Order>> orderModels = orderService.getAllOrders()
                .map(this::toModel);
        return Mono.just(ResponseEntity.ok(orderModels));
    }

    // Endpoint pour récupérer une commande par ID
    @Operation(
            summary = "Get order by ID",
            description = "Fetches a single order by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order found", content = @Content(schema = @Schema(implementation = EntityModel.class))),
                    @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public Mono<ResponseEntity<EntityModel<Order>>> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(this::toModel)
                .map(ResponseEntity::ok);
    }

    // Méthode pour ajouter des liens hypermédia à une commande
    private EntityModel<Order> toModel(Order order) {
        return EntityModel.of(order,
                WebMvcLinkBuilder.linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(OrderController.class).getAllOrders()).withRel("all-orders")
        );
    }
}
