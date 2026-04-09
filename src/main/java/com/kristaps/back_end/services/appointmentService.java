package com.kristaps.back_end.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kristaps.back_end.models.appointmentModel;
import com.kristaps.back_end.repositories.appointmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class appointmentService {
    private final appointmentRepository appointmentRepository;

    public Long createAppointment(appointmentModel appointment) {
        appointmentModel savedAppointment = appointmentRepository.save(appointment);
        return savedAppointment.getId();
    }

    public List<appointmentModel> findAllAppointments() {
        return appointmentRepository.findAll();
    }
}
