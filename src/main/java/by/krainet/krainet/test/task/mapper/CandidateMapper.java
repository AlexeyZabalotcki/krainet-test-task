package by.krainet.krainet.test.task.mapper;

import by.krainet.krainet.test.task.dto.CandidateDto;
import by.krainet.krainet.test.task.model.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    CandidateDto candidateToCandidateDto(Candidate candidate);

    Candidate candidateDtoToCandidate(CandidateDto candidateDTO);

    List<CandidateDto> candidatesToCandidateDtos(List<Candidate> candidates);

    List<Candidate> candidateDtosToCandidates(List<CandidateDto> candidateDTOs);
}
