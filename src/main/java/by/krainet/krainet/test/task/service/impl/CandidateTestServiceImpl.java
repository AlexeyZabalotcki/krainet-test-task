package by.krainet.krainet.test.task.service.impl;

import by.krainet.krainet.test.task.dao.CandidateRepository;
import by.krainet.krainet.test.task.dao.CandidateTestRepository;
import by.krainet.krainet.test.task.dao.TestRepository;
import by.krainet.krainet.test.task.dto.CandidateTestDto;
import by.krainet.krainet.test.task.mapper.CandidateTestMapper;
import by.krainet.krainet.test.task.model.Candidate;
import by.krainet.krainet.test.task.model.CandidateTest;
import by.krainet.krainet.test.task.model.Test;
import by.krainet.krainet.test.task.service.CandidateTestService;
import by.krainet.krainet.test.task.util.GenericSpecification;
import by.krainet.krainet.test.task.util.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateTestServiceImpl implements CandidateTestService {

    private final CandidateTestRepository repository;
    private final CandidateRepository candidateRepository;
    private final TestRepository testRepository;

    private final CandidateTestMapper mapper;

    @Override
    public Page<CandidateTestDto> getAll(Map<String, String> filters, Pageable pageable) {
        GenericSpecification<CandidateTest> spec = new GenericSpecification<>();

        filters.forEach((key, value) -> {
            spec.add(new SearchCriteria(key, ":", value));
        });

        return repository.findAll(spec, pageable)
                .map(mapper::candidateTestToCandidateTestDTO);
    }

    @Override
    public CandidateTestDto create(CandidateTestDto candidateTest) {
        CandidateTest toSave = mapper.candidateTestDTOToCandidateTest(candidateTest);
        CandidateTest saved = repository.save(toSave);
        return mapper.candidateTestToCandidateTestDTO(saved);
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

        CandidateTest saved = repository.save(existingCandidateTest);

        return mapper.candidateTestToCandidateTestDTO(saved);

    }
}
