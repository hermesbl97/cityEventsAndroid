package com.example.cityeventsandr.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {

    private long id;
    private String name;
    private String description;
    private LocalDate eventDate;
    private String category;
    private float price;
    private int capacity;
    private boolean availability;
    private long locationId;
    private List<Long> artistsIds;
}
