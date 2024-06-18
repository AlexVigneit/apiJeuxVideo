package com.waza.apijeuxvideo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ dateEnvoi ne peut pas être null")
    @NotBlank(message = "Le champ dateEnvoi ne peut pas être vide")
    private String dateEnvoi;

    @NotNull(message = "Le champ note ne peut pas être null")
    @Min(value = 1, message = "La note doit être au moins de 1")
    @Max(value = 5, message = "La note doit être au maximum de 5")
    private int note;

    @NotNull(message = "Le champ description ne peut pas être null")
    @NotBlank(message = "Le champ description ne peut pas être vide")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Jeu jeu;

    public Avis(){}

    public Avis(Long id) {
        this.id = id;
    }

    public Avis(String dateEnvoi, int note, String description) {
        this.dateEnvoi = dateEnvoi;
        this.note = note;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
}
