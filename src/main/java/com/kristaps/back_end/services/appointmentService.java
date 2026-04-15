package com.kristaps.back_end.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kristaps.back_end.models.UserAppointmentModel;
import com.kristaps.back_end.models.AppointmentModel;
import com.kristaps.back_end.repositories.UserAppointmentRepository;
import com.kristaps.back_end.repositories.appointmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class appointmentService {
    private final appointmentRepository appointmentRepository;
    private final UserAppointmentRepository userAppointmentRepository;

    public Long createAppointment(AppointmentModel appointment) throws Exception {
        LocalDate appointmentDate = LocalDate.parse(appointment.getAppointmentDate());
        if (appointmentDate.isBefore(LocalDate.now())) {
            throw new Exception("Appointment date cannot be in the past");
        }
        appointment.setCurrentNumberofPeople(0);
        appointment.setRegistered(false);
        AppointmentModel savedAppointment = appointmentRepository.save(appointment);
        return savedAppointment.getId();
    }

    @Transactional(readOnly = true)
    public List<AppointmentModel> findAllAppointments(String email) {
        List<AppointmentModel> appointments = appointmentRepository.findAll();
        for (AppointmentModel appt : appointments) {
            appt.setRegistered(userAppointmentRepository.existsByUserEmailAndAppointmentId(email, appt.getId()));
        }
        return appointments;
    }

    @Transactional
    public void registerForAppointment(Long appointmentId, String email) throws Exception {
        AppointmentModel appt = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new Exception("Appointment not found"));
        if (appt.getCurrentNumberofPeople() >= appt.getMaxNumberofPeople()) {
            throw new Exception("Appointment is full");
        }
        UserAppointmentModel registration = new UserAppointmentModel();
        registration.setUserEmail(email);
        registration.setAppointmentId(appointmentId);
        userAppointmentRepository.save(registration);
        appt.setCurrentNumberofPeople(appt.getCurrentNumberofPeople() + 1);
        appointmentRepository.save(appt);
    }

    @Transactional
    public void cancelRegistration(Long appointmentId, String email) throws Exception {
        userAppointmentRepository.deleteByUserEmailAndAppointmentId(email, appointmentId);
        AppointmentModel appt = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new Exception("Appointment not found"));
        appt.setCurrentNumberofPeople(appt.getCurrentNumberofPeople() - 1);
        appointmentRepository.save(appt);
    }

    @Transactional
    public void deleteAppointment(Long appointmentId) {
        userAppointmentRepository.deleteByAppointmentId(appointmentId);
        appointmentRepository.deleteById(appointmentId);
    }
}
