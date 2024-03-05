package by.krainet.krainet.test.task.service.impl;

import by.krainet.krainet.test.task.dao.CandidateRepository;
import by.krainet.krainet.test.task.dto.CandidateDto;
import by.krainet.krainet.test.task.mapper.CandidateMapper;
import by.krainet.krainet.test.task.model.Candidate;
import by.krainet.krainet.test.task.model.Direction;
import by.krainet.krainet.test.task.service.CandidateService;
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
public class CandidateServiceImpl implements CandidateService {
    
    private final CandidateRepository repository;
    private final CandidateMapper mapper;

    @Override
    public Page<CandidateDto> getAll(Map<String, String> filters, Pageable pageable) {
        GenericSpecification<Candidate> spec = new GenericSpecification<>();

        filters.forEach((key, value) -> {
            spec.add(new SearchCriteria(key, ":", value));
        });

        return repository.findAll(spec, pageable)
                .map(mapper::candidateToCandidateDTO);
    }

    @Override
    public CandidateDto create(CandidateDto direction) {
        Candidate toSave = mapper.candidateDTOToCandidate(direction);
        Candidate saved = repository.save(toSave);
        return mapper.candidateToCandidateDTO(saved);
    }

    @Override
    public CandidateDto update(Long id, CandidateDto direction) {
        Candidate toUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found, Check id " + id));

        toUpdate.setFirstName(direction.firstName());
        toUpdate.setLastName(direction.lastName());
        toUpdate.setMiddleName(direction.middleName());
        toUpdate.setDescription(direction.description());
        toUpdate.setPossibleDirections(direction.possibleDirectionIds());
        toUpdate.setCvFile(direction.cvFile());
        toUpdate.setPhoto(direction.photo());

        return mapper.candidateToCandidateDTO(toUpdate);
    }
}
