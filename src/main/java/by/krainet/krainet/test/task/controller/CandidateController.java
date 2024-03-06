package by.krainet.krainet.test.task.controller;

import by.krainet.krainet.test.task.dto.CandidateDto;
import by.krainet.krainet.test.task.dto.CreateCandidateDto;
import by.krainet.krainet.test.task.dto.Params;
import by.krainet.krainet.test.task.service.CandidateService;
import by.krainet.krainet.test.task.util.NumbersExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService service;

    @GetMapping
    public ResponseEntity<Page<CandidateDto>> getAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                     @RequestParam(defaultValue = "1", required = false) Integer size,
                                                     @RequestParam(defaultValue = "lastName,asc", required = false) String sort,
                                                     @RequestParam(required = false, defaultValue = "") String filter) {
        Params params = new Params(page, size, sort, filter);
        log.info("Input params: {}", params);
        return ResponseEntity.ok(service.getAll(params));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CandidateDto> create(@RequestPart("data") CreateCandidateDto candidateDto,
                                               @RequestParam("photo") MultipartFile photo,
                                               @RequestParam("cvFile") MultipartFile cvFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(candidateDto, photo, cvFile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateDto> update(@PathVariable Long id, @RequestBody CandidateDto candidateDto) {
        return ResponseEntity.ok(service.update(id, candidateDto));
    }
}
