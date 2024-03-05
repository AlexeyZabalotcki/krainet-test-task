package by.krainet.krainet.test.task.service;

import by.krainet.krainet.test.task.dto.CandidateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CandidateService {

    Page<CandidateDto> getAll(Map<String, String> filters, Pageable pageable);

    CandidateDto create(CandidateDto candidate);

    CandidateDto update(Long id, CandidateDto candidate);
}
