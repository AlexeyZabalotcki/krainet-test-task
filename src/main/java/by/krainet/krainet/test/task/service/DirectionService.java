package by.krainet.krainet.test.task.service;

import by.krainet.krainet.test.task.dto.DirectionDto;
import by.krainet.krainet.test.task.dto.Params;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface DirectionService {

    Page<DirectionDto> getAll(Params params);

    DirectionDto create(DirectionDto direction);

    DirectionDto update(Long id, DirectionDto direction);
}
