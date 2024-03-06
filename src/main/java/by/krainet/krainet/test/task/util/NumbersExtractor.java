package by.krainet.krainet.test.task.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumbersExtractor {
    private static List<String> extractor(String string) {
        String regex = "\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        List<String> numbers = new ArrayList<>();
        while (matcher.find()){
            numbers.add(matcher.group());
        }
        return numbers;
    }

    public static List<Long> converter (String strings) {
        return extractor(strings).stream().map(Long::parseLong).collect(Collectors.toList());
    }

}
