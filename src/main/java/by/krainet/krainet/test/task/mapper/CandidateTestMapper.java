package by.krainet.krainet.test.task.mapper;

import by.krainet.krainet.test.task.dto.CandidateTestDto;
import by.krainet.krainet.test.task.dto.TestResultDto;
import by.krainet.krainet.test.task.model.Candidate;
import by.krainet.krainet.test.task.model.CandidateTest;
import by.krainet.krainet.test.task.model.Test;
import by.krainet.krainet.test.task.model.TestResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CandidateTestMapper {

    private final TestResultsMapper testResultsMapper;

    public CandidateTestDto candidateTestToCandidateTestDto(CandidateTest candidateTest) {

        List<TestResultDto> testResultDtos = candidateTest.getTestResults().stream()
                .map(testResultsMapper::testResultToTestResultDto).toList();
        log.info("testResultDtos {}", testResultDtos);

        return new CandidateTestDto(candidateTest.getCandidate().getId(),
                                    candidateTest.getTest().getId(),
                                    testResultDtos);
    }

    public CandidateTest candidateTestDtoToCandidateTest(Candidate candidate,
                                                         Test test,
                                                         CandidateTestDto candidateTest) {

        List<TestResult> testResults = candidateTest.testResults().stream()
                .map(testResultsMapper::testResultDtoToTestResult).toList();
        log.info("testResults {}", testResults);
        return CandidateTest.builder()
                .candidate(candidate)
                .test(test)
                .testResults(testResults)
                .build();
    }
}
