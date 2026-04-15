package com.kristaps.back_end.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kristaps.back_end.models.UserAppointmentModel;

@Repository
public interface UserAppointmentRepository extends JpaRepository<UserAppointmentModel, Long> {

    boolean existsByUserEmailAndAppointmentId(String userEmail, Long appointmentId);

    long countByAppointmentId(Long appointmentId);

    @Transactional
    void deleteByUserEmailAndAppointmentId(String userEmail, Long appointmentId);

    @Transactional
    void deleteByAppointmentId(Long appointmentId);

}
