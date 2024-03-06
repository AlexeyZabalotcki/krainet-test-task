package by.krainet.krainet.test.task.controller;

import by.krainet.krainet.test.task.dto.DirectionDto;
import by.krainet.krainet.test.task.dto.Params;
import by.krainet.krainet.test.task.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/directions")
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService service;

    @GetMapping
    public ResponseEntity<Page<DirectionDto>> getAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                     @RequestParam(defaultValue = "1", required = false) Integer size,
                                                     @RequestParam(defaultValue ="title,asc", required = false) String sort,
                                                     @RequestParam(required = false, defaultValue = "") String filter) {
        Params params = new Params(page, size, sort, filter);
        log.info("Input params: {}", params);
        return ResponseEntity.ok(service.getAll(params));
    }


    @PostMapping
    public ResponseEntity<DirectionDto> create(@RequestBody DirectionDto direction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(direction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectionDto> update(@PathVariable Long id, @RequestBody DirectionDto direction) {
        return ResponseEntity.ok(service.update(id, direction));
    }
}
