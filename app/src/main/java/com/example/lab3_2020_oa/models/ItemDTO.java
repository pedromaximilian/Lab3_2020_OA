package com.example.lab3_2020_oa.models;

import java.io.Serializable;

public class ItemDTO {
    private String prestacionNombre;

    private String prestacionId;

    public ItemDTO(String prestacionNombre, String prestacionId) {
        this.prestacionNombre = prestacionNombre;
        this.prestacionId = prestacionId;
    }

    public ItemDTO() {
    }

    public String getPrestacionNombre() {
        return prestacionNombre;
    }

    public void setPrestacionNombre(String prestacionNombre) {
        this.prestacionNombre = prestacionNombre;
    }

    public String getPrestacionId() {
        return prestacionId;
    }

    public void setPrestacionId(String prestacionId) {
        this.prestacionId = prestacionId;
    }
}
