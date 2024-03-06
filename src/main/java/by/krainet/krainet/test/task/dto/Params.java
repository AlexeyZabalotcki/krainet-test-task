package by.krainet.krainet.test.task.dto;

public record Params(
        Integer page,
        Integer size,
        String sort,
        String filter
) {
}
