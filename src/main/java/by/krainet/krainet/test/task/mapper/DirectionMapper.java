package by.krainet.krainet.test.task.mapper;

import by.krainet.krainet.test.task.dto.DirectionDto;
import by.krainet.krainet.test.task.model.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectionMapper {

    DirectionMapper INSTANCE = Mappers.getMapper(DirectionMapper.class);

    @Mapping(target = "title", source = "direction.title")
    @Mapping(target = "description", source = "direction.description")
    DirectionDto directionToDirectionDto(Direction direction);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    Direction directionDtoToDirection(DirectionDto directionDto);
    List<DirectionDto> directionsToDirectionDtos(List<Direction> direction);
    List<Direction> directionDtosToDirections(List<DirectionDto> directionDtos);
}
