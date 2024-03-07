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
//    private final CandidateMapperI mapper;
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
    public CandidateDto create(CreateCandidateDto candidateDto, MultipartFile photo, MultipartFile cvFile) {
        log.info("CreateCandidateDto {}", candidateDto);
        List<Direction> possibleDirections = getPossibleDirections(candidateDto);

        Candidate candidate = mapper.candidateDtoToCandidate(candidateDto, possibleDirections, photo, cvFile);
        Candidate saved = repository.save(candidate);

//        try {
//            saved = repository.save(Candidate.builder()
//                    .lastName(candidateDto.lastName())
//                    .firstName(candidateDto.firstName())
//                    .middleName(candidateDto.middleName())
//                    .description(candidateDto.description())
//                    .possibleDirections(possibleDirections)
//                    .photo(ImageUtils.compressImage(photo.getBytes()))
//                    .cvFile(cvFile.getBytes())
//                    .build());
//        } catch (IOException e) {
//            throw new RuntimeException("Check CV or photo files");
//        }

        log.info("saved {}", saved);

//        List<Long> directionIds = saved.getPossibleDirections().stream().map(Direction::getId).toList();

        return mapper.candidateToCandidateDto(saved);
//        return new CandidateDto(
//                saved.getLastName(),
//                saved.getFirstName(),
//                saved.getMiddleName(),
//                saved.getPhoto(),
//                saved.getDescription(),
//                saved.getCvFile(),
//                directionIds);
    }

    @Override
    public CandidateDto update(Long id, CreateCandidateDto candidateDto,  MultipartFile photo, MultipartFile cvFile) {
        Candidate toUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found, Check id " + id));


        List<Direction> possibleDirections = getPossibleDirections(candidateDto);
        Candidate updated = mapper.updateCandidateFields(toUpdate, candidateDto, possibleDirections, photo, cvFile);

//        toUpdate.setFirstName(candidateDto.firstName());
//        toUpdate.setLastName(candidateDto.lastName());
//        toUpdate.setMiddleName(candidateDto.middleName());
//        toUpdate.setDescription(candidateDto.description());
//        toUpdate.setPossibleDirections(possibleDirections);
//        try {
//            toUpdate.setCvFile(ImageUtils.compressImage(cvFile.getBytes()));
//            toUpdate.setPhoto(ImageUtils.compressImage(photo.getBytes()));
//        } catch (IOException e) {
//            throw new RuntimeException("Check CV or photo files");
//        }
//
//        Candidate updated = repository.save(toUpdate);

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
