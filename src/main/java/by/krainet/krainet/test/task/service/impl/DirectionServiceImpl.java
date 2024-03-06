package by.krainet.krainet.test.task.service.impl;

import by.krainet.krainet.test.task.dao.DirectionRepository;
import by.krainet.krainet.test.task.dto.DirectionDto;
import by.krainet.krainet.test.task.dto.Params;
import by.krainet.krainet.test.task.mapper.DirectionMapper;
import by.krainet.krainet.test.task.model.Direction;
import by.krainet.krainet.test.task.model.Test;
import by.krainet.krainet.test.task.service.DirectionService;
import by.krainet.krainet.test.task.util.GenericSpecification;
import by.krainet.krainet.test.task.util.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository repository;
    private final DirectionMapper mapper;

    @Override
    public Page<DirectionDto> getAll(Params params) {
        log.info("Service input params: {}", params);
        GenericSpecification<Direction> spec = new GenericSpecification<>(params.filter());

        log.info("GenericSpecification {}", spec);

        String[] sortParams = params.sort().split(",");
        Sort sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(params.page(), params.size(), sort);

        return repository.findAll(spec, pageable)
                .map(mapper::directionToDirectionDto);
    }

    @Override
    public DirectionDto create(DirectionDto direction) {
        log.info("DirectionDto {}", direction.toString());
        Direction toSave = mapper.directionDtoToDirection(direction);
        log.info("toSave {}", toSave);
        Direction saved = repository.save(toSave);
        log.info("saved {}", saved);
        return mapper.directionToDirectionDto(saved);
    }

    @Override
    public DirectionDto update(Long id, DirectionDto direction) {
        log.info("Input params {} {}", id, direction);
        Direction toUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Direction not found, Check id " + id));
        log.info("Direction toUpdate {}", toUpdate);
        toUpdate.setTitle(direction.title());
        toUpdate.setDescription(direction.description());
        Direction updated = repository.save(toUpdate);
        log.info("Updated {}", updated);
        return mapper.directionToDirectionDto(updated);
    }
}
