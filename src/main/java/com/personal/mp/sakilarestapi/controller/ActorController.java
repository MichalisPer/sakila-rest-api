package com.personal.mp.sakilarestapi.controller;

import com.personal.mp.sakilarestapi.dto.ActorDto;
import com.personal.mp.sakilarestapi.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<ActorDto>> getAllActors() {
        List<ActorDto> actors = actorService.getAllActors();
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable int id) {
        ActorDto actor = actorService.getActorById(id);
        if (actor != null) {
            return ResponseEntity.ok(actor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ActorDto> createActor(@RequestBody ActorDto actor) {
        ActorDto savedActor = actorService.saveActor(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDto> updateActor(@PathVariable int id, @RequestBody ActorDto actorDetails) {
        ActorDto actor = actorService.getActorById(id);
        if (actor != null) {
            ActorDto updatedActor = actorService.updateActor(id, actorDetails);
            return ResponseEntity.ok(updatedActor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable int id) {
        if (actorService.deleteActor(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
