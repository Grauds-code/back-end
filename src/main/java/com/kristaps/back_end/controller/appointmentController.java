package com.kristaps.back_end.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kristaps.back_end.models.AppointmentModel;
import com.kristaps.back_end.services.appointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class appointmentController {

    private final appointmentService appointmentService;

    @PostMapping("/api/v2/appointments")
    public ResponseEntity<Long> createAppointment(@RequestBody AppointmentModel appointment) {
        try {
            return new ResponseEntity<>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v2/appointments")
    public ResponseEntity<List<AppointmentModel>> getAllAppointments(@RequestParam String email) {
        try {
            return new ResponseEntity<>(appointmentService.findAllAppointments(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/v2/appointments/{id}/register")
    public ResponseEntity<Void> registerForAppointment(@PathVariable Long id, @RequestParam String email) {
        try {
            appointmentService.registerForAppointment(id, email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/v2/appointments/{id}/register")
    public ResponseEntity<Void> cancelRegistration(@PathVariable Long id, @RequestParam String email) {
        try {
            appointmentService.cancelRegistration(id, email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/v2/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
