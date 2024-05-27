package com.personal.mp.sakilarestapi.service;

import com.personal.mp.sakilarestapi.dto.ActorDto;
import com.personal.mp.sakilarestapi.exception.ResourceNotFoundException;
import com.personal.mp.sakilarestapi.mapper.ActorMapper;
import com.personal.mp.sakilarestapi.model.Actor;
import com.personal.mp.sakilarestapi.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

    private ActorRepository actorRepository;

    private ActorMapper actorMapper;

    @Autowired
    public ActorService(ActorRepository actorRepository, ActorMapper actorMapper) {
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }

    public List<ActorDto> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        List<ActorDto> results = new ArrayList<>();
        for (Actor actor : actors) {
            results.add(actorMapper.toDTO(actor));
        }
        return results;
    }

    public ActorDto getActorById(int actorId) {
        Actor actor = actorRepository.findById(actorId).orElse(null);
        if (actor == null) {
            throw new ResourceNotFoundException("Actor not found");
        }
        return actorMapper.toDTO(actor);
    }

    public ActorDto saveActor(ActorDto actor) {
        return actorMapper.toDTO(actorRepository.save(actorMapper.toEntity(actor)));
    }

    public ActorDto updateActor(int actorId, ActorDto actorDetails) {
        return actorRepository.findById(actorId).map(actor -> {
            actor.setFirstName(actorDetails.getFirstName());
            actor.setLastName(actorDetails.getLastName());
            actor.setLastUpdate(Instant.now());
            return actorMapper.toDTO(actorRepository.save(actor));
        }).orElseThrow(() -> new ResourceNotFoundException("Actor not found"));
    }

    public boolean deleteActor(int actorId) {
        return actorRepository.findById(actorId).map(actor -> {
            actorRepository.delete(actor);
            return true;
        }).orElse(false);
    }
}
