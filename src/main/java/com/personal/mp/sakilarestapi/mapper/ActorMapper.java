package com.personal.mp.sakilarestapi.mapper;

import com.personal.mp.sakilarestapi.dto.ActorDto;
import com.personal.mp.sakilarestapi.model.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {

    public ActorDto toDTO(Actor actor) {
        return new ActorDto(
                actor.getId(),
                actor.getFirstName(),
                actor.getLastName(),
                actor.getLastUpdate());
    }

    public Actor toEntity(ActorDto actorDto) {
        Actor actor = new Actor();
        actor.setId(actorDto.getId());
        actor.setFirstName(actorDto.getFirstName());
        actor.setLastName(actorDto.getLastName());
        actor.setLastUpdate(actorDto.getLastUpdate());
        return actor;
    }
}
