package by.krainet.krainet.test.task.mapper;

import by.krainet.krainet.test.task.dto.CandidateDto;
import by.krainet.krainet.test.task.dto.CreateCandidateDto;
import by.krainet.krainet.test.task.model.Candidate;
import by.krainet.krainet.test.task.model.Direction;
import by.krainet.krainet.test.task.util.FilesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class CandidateMapper {

    public CandidateDto candidateToCandidateDto(Candidate candidate) {
        List<Long> ids = candidate.getPossibleDirections().stream().map(Direction::getId).toList();

        return new CandidateDto(
                candidate.getLastName(),
                candidate.getFirstName(),
                candidate.getMiddleName(),
                candidate.getPhoto(),
                candidate.getDescription(),
                candidate.getCvFile(),
                ids);
    }

    public Candidate candidateDtoToCandidate(CreateCandidateDto candidateDto,
                                             List<Direction> possibleDirections,
                                             MultipartFile photo,
                                             MultipartFile cvFile) {
        try {
            return Candidate.builder()
                    .lastName(candidateDto.lastName())
                    .firstName(candidateDto.firstName())
                    .middleName(candidateDto.middleName())
                    .description(candidateDto.description())
                    .possibleDirections(possibleDirections)
                    .photo(FilesUtils.compressImage(photo.getBytes()))
                    .cvFile(FilesUtils.compressImage(cvFile.getBytes()))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Check CV or photo files");
        }
    }

    public Candidate updateCandidateFields(Candidate candidate,
                                           CreateCandidateDto candidateDto,
                                           List<Direction> possibleDirections,
                                           MultipartFile photo,
                                           MultipartFile cvFile) {
        try {
            candidate.setFirstName(candidateDto.firstName());
            candidate.setLastName(candidateDto.lastName());
            candidate.setMiddleName(candidateDto.middleName());
            candidate.setDescription(candidateDto.description());
            candidate.setPossibleDirections(possibleDirections);
            candidate.setCvFile(FilesUtils.compressImage(cvFile.getBytes()));
            candidate.setPhoto(FilesUtils.compressImage(photo.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("Check CV or photo files");
        }
        return candidate;
    }
}
