package by.krainet.krainet.test.task.controller;

import by.krainet.krainet.test.task.dto.CandidateTestDto;
import by.krainet.krainet.test.task.dto.DirectionDto;
import by.krainet.krainet.test.task.dto.Params;
import by.krainet.krainet.test.task.service.CandidateService;
import by.krainet.krainet.test.task.service.CandidateTestService;
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
@RequestMapping("/api/v1/candidate-tests")
@RequiredArgsConstructor
public class CandidateTestController {

    private final CandidateTestService service;

    @GetMapping
    public ResponseEntity<Page<CandidateTestDto>> getAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                     @RequestParam(defaultValue = "1", required = false) Integer size,
                                                     @RequestParam(defaultValue ="candidateId,asc", required = false) String sort,
                                                     @RequestParam(required = false, defaultValue = "") String filter) {
        Params params = new Params(page, size, sort, filter);
        log.info("Input params: {}", params);
        return ResponseEntity.ok(service.getAll(params));
    }

    @PostMapping
    public ResponseEntity<CandidateTestDto> create(@RequestBody CandidateTestDto candidateTestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(candidateTestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateTestDto> update(@PathVariable Long id, @RequestBody CandidateTestDto candidateTestDto) {
        return ResponseEntity.ok(service.update(id, candidateTestDto));
    }
}
