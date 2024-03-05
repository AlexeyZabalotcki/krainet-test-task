package by.krainet.krainet.test.task.service;

import by.krainet.krainet.test.task.dto.DirectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DirectionService {

    Page<DirectionDto> getAll(String filter, Pageable pageable);

    DirectionDto create(DirectionDto direction);

    DirectionDto update(Long id, DirectionDto direction);
}
