package com.kristaps.back_end.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kristaps.back_end.models.appointmentModel;

@Repository
public interface appointmentRepository extends JpaRepository<appointmentModel, Long> {

}
