package by.krainet.krainet.test.task.service.impl;

import by.krainet.krainet.test.task.dao.CandidateRepository;
import by.krainet.krainet.test.task.dao.DirectionRepository;
import by.krainet.krainet.test.task.dto.CandidateDto;
import by.krainet.krainet.test.task.dto.CreateCandidateDto;
import by.krainet.krainet.test.task.dto.Params;
import by.krainet.krainet.test.task.mapper.CandidateMapper;
import by.krainet.krainet.test.task.model.Candidate;
import by.krainet.krainet.test.task.model.Direction;
import by.krainet.krainet.test.task.service.CandidateService;
import by.krainet.krainet.test.task.util.GenericSpecification;
import by.krainet.krainet.test.task.util.NumbersExtractor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository repository;
    private final DirectionRepository directionRepository;
    private final CandidateMapper mapper;

    @Override
    public Page<CandidateDto> getAll(Params params) {
        log.info("Service input params: {}", params);
        GenericSpecification<Candidate> spec = new GenericSpecification<>(params.filter());

        log.info("GenericSpecification {}", spec);

        String[] sortParams = params.sort().split(",");
        Sort sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(params.page(), params.size(), sort);

        return repository.findAll(spec, pageable)
                .map(mapper::candidateToCandidateDto);
    }

    @Override
    @Transactional
    public CandidateDto create(CreateCandidateDto candidateDto, MultipartFile photo, MultipartFile cvFile) {
        log.info("CreateCandidateDto {}", candidateDto);
        List<Direction> possibleDirections = getPossibleDirections(candidateDto);

        Candidate candidate = mapper.candidateDtoToCandidate(candidateDto, possibleDirections, photo, cvFile);
        Candidate saved = repository.save(candidate);

        log.info("saved {}", saved);

        return mapper.candidateToCandidateDto(saved);
    }

    @Override
    @Transactional
    public CandidateDto update(Long id, CreateCandidateDto candidateDto,  MultipartFile photo, MultipartFile cvFile) {
        Candidate toUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found, Check id " + id));


        List<Direction> possibleDirections = getPossibleDirections(candidateDto);
        Candidate updated = mapper.updateCandidateFields(toUpdate, candidateDto, possibleDirections, photo, cvFile);

        Candidate saved = repository.save(updated);
        return mapper.candidateToCandidateDto(saved);
    }

    private List<Direction> getPossibleDirections(CreateCandidateDto candidateDto) {
        List<Long> ids = NumbersExtractor.converter(candidateDto.possibleDirectionIds());

        List<Direction> possibleDirections = directionRepository.findAllById(ids);
        log.info("possibleDirections {}", possibleDirections);
        return possibleDirections;
    }
}
