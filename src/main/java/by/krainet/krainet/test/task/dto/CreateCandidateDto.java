package by.krainet.krainet.test.task.dto;

import java.util.List;

public record CreateCandidateDto(
        String lastName,
        String firstName,
        String middleName,
        String description,
        String possibleDirectionIds
) {
}
