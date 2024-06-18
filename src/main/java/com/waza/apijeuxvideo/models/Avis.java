package com.waza.apijeuxvideo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateEnvoi;
    private int note;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Jeu jeu;

    public Avis(){}

    public Avis(Long id) {
        this.id = id;
    }

    public Avis(String dateEnvoi, int note, String description, Jeu jeu) {
        this.dateEnvoi = dateEnvoi;
        this.note = note;
        this.description = description;
        this.jeu = jeu;
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
