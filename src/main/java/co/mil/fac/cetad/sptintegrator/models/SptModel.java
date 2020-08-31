package co.mil.fac.cetad.sptintegrator.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SptModel {

    private String id;
    private String name;
    private String location;
    private LocalDateTime created;

}
