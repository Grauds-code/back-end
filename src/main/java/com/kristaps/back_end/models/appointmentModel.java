package com.kristaps.back_end.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class appointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String AppointmentName;
    String AppointmentDate;
    String AppointmentTime;
    String AppointmentLocation;
    Integer MaxNumberofPeople;
    Integer CurrentNumberofPeople;
    String Description;
    Boolean Registered;
}
