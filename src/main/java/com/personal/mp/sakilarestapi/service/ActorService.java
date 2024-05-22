package com.personal.mp.sakilarestapi.service;

import com.personal.mp.sakilarestapi.exception.ResourceNotFoundException;
import com.personal.mp.sakilarestapi.model.Actor;
import com.personal.mp.sakilarestapi.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Optional<Actor> getActorById(int actor_id) {
        return actorRepository.findById(actor_id);
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor updateActor(int id, Actor actorDetails) {
        return actorRepository.findById(id).map(actor -> {
            actor.setFirstName(actorDetails.getFirstName());
            actor.setLastName(actorDetails.getLastName());
            actor.setLastUpdate(Instant.now());
            return actorRepository.save(actor);
        }).orElseThrow(() -> new ResourceNotFoundException("Actor not found"));
    }

    public boolean deleteActor(int id) {
        return actorRepository.findById(id).map(actor -> {
            actorRepository.delete(actor);
            return true;
        }).orElse(false);
    }
}
