package com.kristaps.back_end.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kristaps.back_end.models.UserAppointmentModel;
import com.kristaps.back_end.models.appointmentModel;
import com.kristaps.back_end.repositories.UserAppointmentRepository;
import com.kristaps.back_end.repositories.appointmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class appointmentService {
    private final appointmentRepository appointmentRepository;
    private final UserAppointmentRepository userAppointmentRepository;

    public Long createAppointment(appointmentModel appointment) {
        LocalDate appointmentDate = LocalDate.parse(appointment.getAppointmentDate());
        if (appointmentDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past");
        }
        appointment.setCurrentNumberofPeople(0);
        appointment.setRegistered(false);
        appointmentModel savedAppointment = appointmentRepository.save(appointment);
        return savedAppointment.getId();
    }

    @Transactional(readOnly = true)
    public List<appointmentModel> findAllAppointments(String email) {
        List<appointmentModel> appointments = appointmentRepository.findAll();
        for (appointmentModel appt : appointments) {
            appt.setRegistered(userAppointmentRepository.existsByUserEmailAndAppointmentId(email, appt.getId()));
        }
        return appointments;
    }

    @Transactional
    public void registerForAppointment(Long appointmentId, String email) {
        appointmentModel appt = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalStateException("Appointment not found"));
        if (appt.getCurrentNumberofPeople() >= appt.getMaxNumberofPeople()) {
            throw new IllegalStateException("Appointment is full");
        }
        UserAppointmentModel registration = new UserAppointmentModel();
        registration.setUserEmail(email);
        registration.setAppointmentId(appointmentId);
        userAppointmentRepository.save(registration);
        appt.setCurrentNumberofPeople(appt.getCurrentNumberofPeople() + 1);
        appointmentRepository.save(appt);
    }

    @Transactional
    public void cancelRegistration(Long appointmentId, String email) {
        userAppointmentRepository.deleteByUserEmailAndAppointmentId(email, appointmentId);
        appointmentModel appt = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalStateException("Appointment not found"));
        appt.setCurrentNumberofPeople(appt.getCurrentNumberofPeople() - 1);
        appointmentRepository.save(appt);
    }

    @Transactional
    public void updateAppointment(Long appointmentId, appointmentModel updated) {
        appointmentModel existing = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalStateException("Appointment not found"));
        existing.setAppointmentName(updated.getAppointmentName());
        existing.setAppointmentDate(updated.getAppointmentDate());
        existing.setAppointmentTime(updated.getAppointmentTime());
        existing.setAppointmentLocation(updated.getAppointmentLocation());
        existing.setMaxNumberofPeople(updated.getMaxNumberofPeople());
        existing.setDescription(updated.getDescription());
        appointmentRepository.save(existing);
    }

    @Transactional
    public void deleteAppointment(Long appointmentId) {
        userAppointmentRepository.deleteByAppointmentId(appointmentId);
        appointmentRepository.deleteById(appointmentId);
    }
}
