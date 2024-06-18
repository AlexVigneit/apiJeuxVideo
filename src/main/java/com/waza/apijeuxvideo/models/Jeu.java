package com.waza.apijeuxvideo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ nom ne peut pas être null")
    @NotBlank(message = "Le champ nom ne peut pas être vide")
    private String nom;

    @NotNull(message = "Le champ description ne peut pas être null")
    @NotBlank(message = "Le champ description ne peut pas être vide")
    private String description;

    @NotNull(message = "Le champ dateSortie ne peut pas être null")
    @NotBlank(message = "Le champ dateSortie ne peut pas être vide")
    private String dateSortie;

    @OneToMany(mappedBy="jeu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Avis> avis;

    public Jeu() {}

    public Jeu(Long id) {
        this.id = id;
    }

    public Jeu(String nom, String description, String dateSortie, List<Avis> avis) {
        this.nom = nom;
        this.description = description;
        this.dateSortie = dateSortie;
        this.avis = avis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }
}
