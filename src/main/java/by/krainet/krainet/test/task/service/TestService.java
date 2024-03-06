package by.krainet.krainet.test.task.service;

import by.krainet.krainet.test.task.dto.Params;
import by.krainet.krainet.test.task.dto.TestDto;
import org.springframework.data.domain.Page;

public interface TestService {

    Page<TestDto> getAll(Params params);

    TestDto create(TestDto test);

    TestDto update(Long id, TestDto test);
}
