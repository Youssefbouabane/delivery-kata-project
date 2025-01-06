package com.kata.delivery.repository;

import com.kata.delivery.constant.DeliveryMode;
import com.kata.delivery.entity.TimeSlot;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface TimeSlotRepository extends ReactiveCrudRepository<TimeSlot, Long> {

    Flux<TimeSlot> findByDeliveryModeAndDateAndReservedFalse(DeliveryMode deliveryMode, LocalDate date);

}
