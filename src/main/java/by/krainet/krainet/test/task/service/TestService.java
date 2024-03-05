package by.krainet.krainet.test.task.service;

import by.krainet.krainet.test.task.dto.TestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TestService {

    Page<TestDto> getAll(Map<String, String> filters, Pageable pageable);

    TestDto create(TestDto test);

    TestDto update(Long id, TestDto test);
}
