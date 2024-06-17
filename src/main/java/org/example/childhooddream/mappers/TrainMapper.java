package org.example.childhooddream.mappers;

import org.example.childhooddream.dto.TrainDTO;
import org.example.childhooddream.entities.Train;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrainMapper {

    Train toEntity(TrainDTO trainDTO);

    TrainDTO toDTO(Train train);

    void updateEntityFromDTO(TrainDTO trainDTO, @MappingTarget Train train);
}
