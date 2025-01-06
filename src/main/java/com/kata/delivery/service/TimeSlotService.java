package com.kata.delivery.service;

import com.kata.delivery.constant.DeliveryMode;
import com.kata.delivery.entity.TimeSlot;
import com.kata.delivery.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public Flux<TimeSlot> getAvailableSlots(DeliveryMode deliveryMode, LocalDate date) {
        return timeSlotRepository.findByDeliveryModeAndDateAndReservedFalse(deliveryMode, date);
    }

    public Mono<TimeSlot> reserveTimeSlot(Long timeSlotId) {
        return timeSlotRepository.findById(timeSlotId)
                .switchIfEmpty(Mono.error(new RuntimeException("TimeSlot not found")))
                .flatMap(timeSlot -> {
                    if (timeSlot.isReserved()) {
                        return Mono.error(new RuntimeException("TimeSlot already reserved"));
                    }
                    timeSlot.setReserved(true);
                    return timeSlotRepository.save(timeSlot);
                });
    }
}
