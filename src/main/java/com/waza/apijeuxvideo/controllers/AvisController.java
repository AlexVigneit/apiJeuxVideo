package com.waza.apijeuxvideo.controllers;

import com.waza.apijeuxvideo.models.Avis;
import com.waza.apijeuxvideo.models.Jeu;
import com.waza.apijeuxvideo.repository.AvisRepository;
import com.waza.apijeuxvideo.repository.JeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avis")
public class AvisController {

    @Autowired
    private AvisRepository avisRepository;

    @Autowired
    private JeuRepository jeuRepository;

    @PostMapping("/{jeuId}")
    public ResponseEntity<Avis> addAvis(@PathVariable Long jeuId, @RequestBody Avis avis) {
        Jeu jeu = jeuRepository.findById(jeuId).orElse(null);
        if (jeu != null) {
            avis.setJeu(jeu);
            Avis savedAvis = avisRepository.save(avis);
            return new ResponseEntity<>(savedAvis, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avis> updateAvis(@PathVariable Long id, @RequestBody Avis avis) {
        Avis existingAvis = avisRepository.findById(id).orElse(null);
        if (existingAvis != null) {
            existingAvis.setDateEnvoi(avis.getDateEnvoi());
            existingAvis.setNote(avis.getNote());
            existingAvis.setDescription(avis.getDescription());
            Avis updatedAvis = avisRepository.save(existingAvis);
            return new ResponseEntity<>(updatedAvis, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvis(@PathVariable Long id) {
        avisRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/jeux/{jeuId}")
    public ResponseEntity<List<Avis>> getAvisByJeu(@PathVariable Long jeuId) {
        Jeu jeu = jeuRepository.findById(jeuId).orElse(null);
        if (jeu != null) {
            List<Avis> avis = jeu.getAvis();
            return new ResponseEntity<>(avis, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
