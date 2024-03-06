package by.krainet.krainet.test.task.service.impl;

import by.krainet.krainet.test.task.dao.TestRepository;
import by.krainet.krainet.test.task.dto.Params;
import by.krainet.krainet.test.task.dto.TestDto;
import by.krainet.krainet.test.task.mapper.TestMapper;
import by.krainet.krainet.test.task.model.Test;
import by.krainet.krainet.test.task.service.TestService;
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
public class TestServiceImpl implements TestService {

    private final TestRepository repository;
    private final TestMapper mapper;

    @Override
    public Page<TestDto> getAll(Params params) {
        log.info("Service input params: {}", params);
        GenericSpecification<Test> spec = new GenericSpecification<>(params.filter());

        log.info("GenericSpecification {}", spec);

        String[] sortParams = params.sort().split(",");
        Sort sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(params.page(), params.size(), sort);

        return repository.findAll(spec, pageable)
                .map(mapper::testToTestDto);
    }

    @Override
    public TestDto create(TestDto test) {
        Test toSave = mapper.testDtoToTest(test);
        Test saved = repository.save(toSave);
        return mapper.testToTestDto(saved);
    }

    @Override
    public TestDto update(Long id, TestDto test) {
        Test toUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Test not found, Check id " + id));

        toUpdate.setTitle(test.title());
        toUpdate.setDescription(test.description());

        Test updated = repository.save(toUpdate);

        return mapper.testToTestDto(updated);
    }
}
