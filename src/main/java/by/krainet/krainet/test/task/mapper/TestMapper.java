package by.krainet.krainet.test.task.mapper;

import by.krainet.krainet.test.task.dto.TestDto;
import by.krainet.krainet.test.task.model.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    TestDto testToTestDto(Test test);

    Test testDtoToTest(TestDto testDTO);

    List<TestDto> testsToTestDtos(List<Test> tests);

    List<Test> testDtosToTests(List<TestDto> testDTOs);
}
