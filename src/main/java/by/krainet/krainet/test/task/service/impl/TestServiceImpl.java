package by.krainet.krainet.test.task.service.impl;

import by.krainet.krainet.test.task.dao.TestRepository;
import by.krainet.krainet.test.task.dto.TestDto;
import by.krainet.krainet.test.task.mapper.TestMapper;
import by.krainet.krainet.test.task.model.Candidate;
import by.krainet.krainet.test.task.model.CandidateTest;
import by.krainet.krainet.test.task.model.Test;
import by.krainet.krainet.test.task.model.TestResult;
import by.krainet.krainet.test.task.service.TestService;
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
public class TestServiceImpl implements TestService {

    private final TestRepository repository;
    private final TestMapper mapper;

    @Override
    public Page<TestDto> getAll(Map<String, String> filters, Pageable pageable) {
        GenericSpecification<Test> spec = new GenericSpecification<>();

        filters.forEach((key, value) -> {
            spec.add(new SearchCriteria(key, ":", value));
        });

        return repository.findAll(spec, pageable)
                .map(mapper::testToTestDTO);
    }

    @Override
    public TestDto create(TestDto test) {
        Test toSave = mapper.testDTOToTest(test);
        Test saved = repository.save(toSave);
        return mapper.testToTestDTO(saved);
    }

    @Override
    public TestDto update(Long id, TestDto test) {
        Test toUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Test not found, Check id " + id));

        toUpdate.setTitle(test.title());
        toUpdate.setDescription(test.description());

        return mapper.testToTestDTO(toUpdate);
    }
}
