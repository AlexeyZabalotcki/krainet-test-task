package by.krainet.krainet.test.task.service.impl;

import by.krainet.krainet.test.task.dao.DirectionRepository;
import by.krainet.krainet.test.task.dto.DirectionDto;
import by.krainet.krainet.test.task.mapper.DirectionMapper;
import by.krainet.krainet.test.task.model.Direction;
import by.krainet.krainet.test.task.service.DirectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository repository;
    private final DirectionMapper mapper;

    @Override
    public Page<DirectionDto> getAll(String filter, Pageable pageable) {
        if (filter == null || filter.isEmpty()) {
            return repository.findAll(pageable).map(mapper::directionToDirectionDto);
        } else {
            return repository.findByTitleContainingIgnoreCase(filter, pageable).map(mapper::directionToDirectionDto);
        }
    }

    @Override
    public DirectionDto create(DirectionDto direction) {
        Direction toSave = mapper.directionDtoToDirection(direction);
        Direction saved = repository.save(toSave);
        return mapper.directionToDirectionDto(saved);
    }

    @Override
    public DirectionDto update(Long id, DirectionDto direction) {
        Direction toUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Direction not found, Check id " + id));
        toUpdate.setTitle(direction.title());
        toUpdate.setDescription(direction.description());
        return mapper.directionToDirectionDto(toUpdate);
    }
}
