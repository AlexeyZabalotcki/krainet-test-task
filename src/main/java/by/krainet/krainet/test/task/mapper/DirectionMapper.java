package by.krainet.krainet.test.task.mapper;

import by.krainet.krainet.test.task.dto.DirectionDto;
import by.krainet.krainet.test.task.model.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectionMapper {

    DirectionMapper INSTANCE = Mappers.getMapper(DirectionMapper.class);

    DirectionDto directionToDirectionDto(Direction direction);
    Direction directionDtoToDirection(DirectionDto direction);
    List<DirectionDto> directionsToDirectionDtos(List<Direction> direction);
    List<Direction> directionDtosToDirections(List<DirectionDto> direction);
}
