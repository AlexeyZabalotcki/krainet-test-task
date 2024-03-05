package by.krainet.krainet.test.task.service;

import by.krainet.krainet.test.task.dto.CandidateTestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CandidateTestService {

    Page<CandidateTestDto> getAll(Map<String, String> filters, Pageable pageable);

    CandidateTestDto create(CandidateTestDto candidateTest);

    CandidateTestDto update(Long id, CandidateTestDto candidateTest);
}
