package com.waza.apijeuxvideo.controllers;

import com.waza.apijeuxvideo.models.Avis;
import com.waza.apijeuxvideo.models.Jeu;
import com.waza.apijeuxvideo.repository.JeuRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
public class JeuController {

    @Autowired
    private JeuRepository jeuRepository;

    @PostMapping("/")
    public ResponseEntity<Jeu> addJeu(@Valid @RequestBody Jeu jeu) {
        Jeu savedJeu = jeuRepository.save(jeu);
        return new ResponseEntity<>(savedJeu, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jeu> updateJeu(@PathVariable Long id, @RequestBody Jeu jeu) {
        Jeu existingJeu = jeuRepository.findById(id).orElse(null);
        if (existingJeu != null) {
            existingJeu.setNom(jeu.getNom());
            existingJeu.setDescription(jeu.getDescription());
            existingJeu.setDateSortie(jeu.getDateSortie());
            Jeu updatedJeu = jeuRepository.save(existingJeu);
            return new ResponseEntity<>(updatedJeu, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJeu(@PathVariable Long id) {
        jeuRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jeu> getJeu(@PathVariable Long id) {
        Jeu jeu = jeuRepository.findById(id).orElse(null);
        return jeu != null ? new ResponseEntity<>(jeu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<Jeu>> getAllJeux() {
        List<Jeu> jeux = jeuRepository.findAll();
        return new ResponseEntity<>(jeux, HttpStatus.OK);
    }

    @GetMapping("/{id}/dernierAvis")
    public ResponseEntity<Avis> getDernierAvis(@PathVariable Long id) {
        Jeu jeu = jeuRepository.findById(id).orElse(null);
        if (jeu != null && !jeu.getAvis().isEmpty()) {
            Avis dernierAvis = jeu.getAvis().get(jeu.getAvis().size() - 1);
            return new ResponseEntity<>(dernierAvis, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
