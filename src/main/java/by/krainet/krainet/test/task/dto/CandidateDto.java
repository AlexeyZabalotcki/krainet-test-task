package by.krainet.krainet.test.task.dto;

import java.util.List;

public record CandidateDto(
        String lastName,
        String firstName,
        String middleName,
        byte[] photo,
        String description,
        byte[] cvFile,
        List<Long>possibleDirectionIds
) {
}
