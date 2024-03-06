package by.krainet.krainet.test.task.service.impl;

import by.krainet.krainet.test.task.dao.CandidateRepository;
import by.krainet.krainet.test.task.dao.CandidateTestRepository;
import by.krainet.krainet.test.task.dao.TestRepository;
import by.krainet.krainet.test.task.dto.CandidateTestDto;
import by.krainet.krainet.test.task.dto.Params;
import by.krainet.krainet.test.task.mapper.CandidateTestMapper;
import by.krainet.krainet.test.task.model.Candidate;
import by.krainet.krainet.test.task.model.CandidateTest;
import by.krainet.krainet.test.task.model.Direction;
import by.krainet.krainet.test.task.model.Test;
import by.krainet.krainet.test.task.service.CandidateTestService;
import by.krainet.krainet.test.task.util.GenericSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateTestServiceImpl implements CandidateTestService {

    private final CandidateTestRepository repository;
    private final CandidateRepository candidateRepository;
    private final TestRepository testRepository;

    private final CandidateTestMapper mapper;

    @Override
    public Page<CandidateTestDto> getAll(Params params) {
        log.info("Service input params: {}", params);
        GenericSpecification<CandidateTest> spec = new GenericSpecification<>(params.filter());

        log.info("GenericSpecification {}", spec);

        String[] sortParams = params.sort().split(",");
        Sort sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(params.page(), params.size(), sort);

        return repository.findAll(spec, pageable)
                .map(mapper::candidateTestToCandidateTestDto);
    }

    @Override
    public CandidateTestDto create(CandidateTestDto candidateTest) {
        CandidateTest toSave = mapper.candidateTestDtoToCandidateTest(candidateTest);
        CandidateTest saved = repository.save(toSave);
        return mapper.candidateTestToCandidateTestDto(saved);
    }

    @Override
    public CandidateTestDto update(Long id, CandidateTestDto candidateTest) {
        CandidateTest existingCandidateTest = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CandidateTest not found with id: " + id));

        if (candidateTest.testId() != null) {
            Candidate candidate = candidateRepository.findById(candidateTest.candidateId())
                    .orElseThrow(() -> new EntityNotFoundException("Candidate not found with id: " + candidateTest.candidateId()));
            existingCandidateTest.setCandidate(candidate);
        }
        if (candidateTest.testId() != null) {
            Test test = testRepository.findById(candidateTest.testId())
                    .orElseThrow(() -> new EntityNotFoundException("Test not found with id: " + candidateTest.testId()));
            existingCandidateTest.setTest(test);
        }

        CandidateTest updated = repository.save(existingCandidateTest);

        return mapper.candidateTestToCandidateTestDto(updated);

    }
}
