package com.kata.delivery.controller;

import com.kata.delivery.constant.DeliveryMode;
import com.kata.delivery.entity.TimeSlot;
import com.kata.delivery.service.TimeSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/timeslots")
@Tag(name = "TimeSlot API", description = "Endpoints for managing time slots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    // Endpoint pour récupérer les créneaux disponibles avec HATEOAS
    @Operation(
            summary = "Get available time slots",
            description = "Fetch all available time slots for a given delivery mode and date",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters provided", content = @Content)
            },
            security = @SecurityRequirement(name = "JWT")

    )
    @GetMapping
    public Mono<ResponseEntity<Flux<EntityModel<TimeSlot>>>> getAvailableTimeSlots(
            @RequestParam DeliveryMode deliveryMode,
            @RequestParam(required = false) String date) {

        LocalDate localDate = date != null ? LocalDate.parse(date) : LocalDate.now();
        Flux<EntityModel<TimeSlot>> timeSlotModels = timeSlotService.getAvailableSlots(deliveryMode, localDate)
                .map(this::toModel);

        return Mono.just(ResponseEntity.ok(timeSlotModels));
    }

    // Endpoint pour réserver un créneau avec HATEOAS
    @Operation(
            summary = "Reserve a time slot",
            description = "Reserve a specific time slot by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Time slot reserved successfully", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Time slot not found", content = @Content)
            }
    )
    @PostMapping("/{id}/reserve")
    public Mono<ResponseEntity<EntityModel<TimeSlot>>> reserveTimeSlot(@PathVariable Long id) {
        return timeSlotService.reserveTimeSlot(id)
                .map(this::toModel)
                .map(ResponseEntity::ok);
    }

    // Méthode pour convertir un TimeSlot en EntityModel avec des liens HATEOAS
    private EntityModel<TimeSlot> toModel(TimeSlot timeSlot) {
        return EntityModel.of(timeSlot,
                linkTo(methodOn(TimeSlotController.class).getAvailableTimeSlots(
                        timeSlot.getDeliveryMode(), timeSlot.getDate().toString()
                )).withRel("available-slots"),
                linkTo(methodOn(TimeSlotController.class).reserveTimeSlot(timeSlot.getId()))
                        .withRel("reserve-slot")
        );
    }
}
