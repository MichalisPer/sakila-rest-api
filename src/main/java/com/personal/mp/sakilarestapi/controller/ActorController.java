package com.personal.mp.sakilarestapi.controller;

import com.personal.mp.sakilarestapi.model.Actor;
import com.personal.mp.sakilarestapi.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actors = actorService.getAllActors();
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorByID(@PathVariable int id) {
        Optional<Actor> actor = actorService.getActorById(id);
        if (actor.isPresent()) {
            return ResponseEntity.ok(actor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/actors")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        Actor savedActor = actorService.saveActor(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
    }

    @PutMapping("/actors/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable int id, @RequestBody Actor actorDetails) {
        Optional<Actor> actor = actorService.getActorById(id);
        if (actor.isPresent()) {
            Actor updatedActor = actorService.updateActor(id, actorDetails);
            return ResponseEntity.ok(updatedActor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/actors/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable int id) {
        if (actorService.deleteActor(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
