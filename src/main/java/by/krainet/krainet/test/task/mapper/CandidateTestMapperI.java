package by.krainet.krainet.test.task.mapper;

import by.krainet.krainet.test.task.dto.CandidateTestDto;
import by.krainet.krainet.test.task.model.CandidateTest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CandidateMapper.class, TestMapper.class})
public interface CandidateTestMapperI {

    CandidateTestMapperI INSTANCE = Mappers.getMapper(CandidateTestMapperI.class);

    @Mapping(target = "candidateId", source = "candidate.id")
    @Mapping(target = "testId", source = "test.id")
    @Mapping(target = "testResults", source = "testResults")
    CandidateTestDto candidateTestToCandidateTestDto(CandidateTest candidateTest);

    CandidateTest candidateTestDtoToCandidateTest(CandidateTestDto candidateTestDTO);

    List<CandidateTestDto> candidateTestsToCandidateTestDtos(List<CandidateTest> candidateTests);

    List<CandidateTest> candidateTestDtosToCandidateTests(List<CandidateTestDto> candidateTestDTOs);
}