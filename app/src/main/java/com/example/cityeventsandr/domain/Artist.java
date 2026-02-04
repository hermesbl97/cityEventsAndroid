package com.example.cityeventsandr.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artist implements Serializable {   //lo hacemos serializable para poder pasar el objto artista a través de un Intent
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
