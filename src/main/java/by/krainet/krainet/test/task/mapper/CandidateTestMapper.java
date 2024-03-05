package by.krainet.krainet.test.task.mapper;

import by.krainet.krainet.test.task.dto.CandidateTestDto;
import by.krainet.krainet.test.task.model.CandidateTest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CandidateMapper.class, TestMapper.class})
public interface CandidateTestMapper {

    CandidateTestMapper INSTANCE = Mappers.getMapper(CandidateTestMapper.class);

    CandidateTestDto candidateTestToCandidateTestDTO(CandidateTest candidateTest);

    CandidateTest candidateTestDTOToCandidateTest(CandidateTestDto candidateTestDTO);

    List<CandidateTestDto> candidateTestsToCandidateTestDTOs(List<CandidateTest> candidateTests);

    List<CandidateTest> candidateTestDTOsToCandidateTests(List<CandidateTestDto> candidateTestDTOs);
}