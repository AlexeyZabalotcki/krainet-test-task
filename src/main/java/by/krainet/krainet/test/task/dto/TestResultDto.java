package by.krainet.krainet.test.task.dto;

import java.time.LocalDate;

public record TestResultDto(
        LocalDate date,
        Double score
) {
}
