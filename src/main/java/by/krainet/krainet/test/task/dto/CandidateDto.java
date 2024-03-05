package by.krainet.krainet.test.task.dto;

import by.krainet.krainet.test.task.model.Direction;

import java.util.List;

public record CandidateDto(
        String lastName,
        String firstName,
        String middleName,
        byte[]photo,
        String description,
        byte[]cvFile,
        List<Direction>possibleDirectionIds
) {
}
