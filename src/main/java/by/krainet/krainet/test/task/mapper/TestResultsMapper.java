package by.krainet.krainet.test.task.mapper;

import by.krainet.krainet.test.task.dto.CandidateTestDto;
import by.krainet.krainet.test.task.dto.TestResultDto;
import by.krainet.krainet.test.task.model.Candidate;
import by.krainet.krainet.test.task.model.CandidateTest;
import by.krainet.krainet.test.task.model.TestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestResultsMapper {

    public TestResultDto testResultToTestResultDto (TestResult testResult){
        return new TestResultDto(testResult.getDate(), testResult.getScore());
    }

    public TestResult testResultDtoToTestResult (TestResultDto testResult){
        return TestResult.builder()
                .date(testResult.date())
                .score(testResult.score())
                .build();
    }
}
