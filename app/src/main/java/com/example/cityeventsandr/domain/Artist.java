package com.example.cityeventsandr.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artist {
    private long id;
    private String name;
    private String surname;
    private String genre;
    private LocalDate birthDate;
    private String type;
    private int followers;
    private float height;
    private boolean active;
    private List<Event> events;
}
