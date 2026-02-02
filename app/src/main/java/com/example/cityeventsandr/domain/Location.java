package com.example.cityeventsandr.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {

    private long id;
    private  String name;
    private String description;
    private String category;
    private String streetLocated;
    private int postalCode;
    private LocalDate registerDate;
    private boolean disabledAccess;
    private double latitude;
    private double longitude;
    private List<Event> events;
}
