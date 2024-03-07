package by.krainet.krainet.test.task.dto;

public record CreateCandidateDto(
        String lastName,
        String firstName,
        String middleName,
        String description,
        String possibleDirectionIds
) {
}
