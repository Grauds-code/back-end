package com.kristaps.back_end.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("AppointmentName")
    String AppointmentName;

    @JsonProperty("AppointmentDate")
    String AppointmentDate;

    @JsonProperty("AppointmentTime")
    String AppointmentTime;

    @JsonProperty("AppointmentLocation")
    String AppointmentLocation;

    @JsonProperty("MaxNumberofPeople")
    Integer MaxNumberofPeople;

    @JsonProperty("CurrentNumberofPeople")
    Integer CurrentNumberofPeople;

    @JsonProperty("Description")
    String Description;

    @JsonProperty("Registered")
    Boolean Registered;
}
