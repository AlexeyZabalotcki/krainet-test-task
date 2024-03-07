package by.krainet.krainet.test.task.service;

import by.krainet.krainet.test.task.dto.CandidateDto;
import by.krainet.krainet.test.task.dto.CreateCandidateDto;
import by.krainet.krainet.test.task.dto.Params;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CandidateService {

    Page<CandidateDto> getAll(Params params);

    CandidateDto create(CreateCandidateDto candidateDto, MultipartFile photo, MultipartFile cvFile);

    CandidateDto update(Long id, CreateCandidateDto candidateDto, MultipartFile photo, MultipartFile cvFile);
}
