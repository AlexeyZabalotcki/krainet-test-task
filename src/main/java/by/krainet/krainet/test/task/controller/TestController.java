package by.krainet.krainet.test.task.controller;

import by.krainet.krainet.test.task.dto.DirectionDto;
import by.krainet.krainet.test.task.dto.Params;
import by.krainet.krainet.test.task.dto.TestDto;
import by.krainet.krainet.test.task.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @GetMapping
    public ResponseEntity<Page<TestDto>> getAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                     @RequestParam(defaultValue = "1", required = false) Integer size,
                                                     @RequestParam(defaultValue ="title,asc", required = false) String sort,
                                                     @RequestParam(required = false, defaultValue = "") String filter) {
        Params params = new Params(page, size, sort, filter);
        log.info("Input params: {}", params);
        return ResponseEntity.ok(service.getAll(params));
    }

    @PostMapping
    public ResponseEntity<TestDto> create(@RequestBody TestDto testDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(testDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestDto> update(@PathVariable Long id, @RequestBody TestDto testDto) {
        return ResponseEntity.ok(service.update(id, testDto));
    }
}
