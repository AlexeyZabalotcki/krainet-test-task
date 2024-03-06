package by.krainet.krainet.test.task.service;

import by.krainet.krainet.test.task.dto.CandidateTestDto;
import by.krainet.krainet.test.task.dto.Params;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Parameter;
import java.util.Map;

public interface CandidateTestService {

    Page<CandidateTestDto> getAll(Params params);

    CandidateTestDto create(CandidateTestDto candidateTest);

    CandidateTestDto update(Long id, CandidateTestDto candidateTest);
}
