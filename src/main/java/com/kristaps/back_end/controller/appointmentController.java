package com.kristaps.back_end.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kristaps.back_end.models.appointmentModel;
import com.kristaps.back_end.services.appointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin

public class appointmentController {

    private final appointmentService appointmentService;

    @PostMapping("/api/v2/appointments")
    public ResponseEntity<Long> createAppointment(@RequestBody appointmentModel appointment) {
        return new ResponseEntity<>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
    }

    @GetMapping("/api/v2/appointments")
    public ResponseEntity<List<appointmentModel>> getAllAppointments() {
        try {
            return new ResponseEntity<>(appointmentService.findAllAppointments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
