package by.krainet.krainet.test.task.dto;

import java.util.List;

public record CandidateTestDto(
        Long candidateId,
        Long testId,
        List<TestResultDto>testResults
) {
}
