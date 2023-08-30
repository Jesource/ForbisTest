package forbis.preselection.assignment.dto;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultRecordDtoTest {

    @Test
    @org.junit.Test
    public void givenJsonStringWithOneTokenGroup_whenMappingJsonStringToResultRecordDto_thenNoErrorsExpected() {
        String jsonString = "{\"formatted_result\":[\"o 1 hello\"],\"timestamp\":\"2023-08-29T19:45:46.304415\"}";
        ResultRecordDto recordDto = new ResultRecordDto(jsonString);

        assertAll(
                () -> assertEquals(List.of("o 1 hello"), recordDto.getTokenGroups()),
                () -> assertEquals(LocalDateTime.parse("2023-08-29T19:45:46.304415"), recordDto.getTimestamp())
        );
    }


    @Test
    @org.junit.Test
    public void givenJsonStringWithTwoTokenGroup_whenMappingJsonStringToResultRecordDto_thenNoErrorsExpected() {
        String jsonString = "{\"formatted_result\":[\"t 1 TEXT\",\"y 1 ONLY\"],\"timestamp\":\"2023-08-29T19:04:24.892967\"}";
        ResultRecordDto recordDto = new ResultRecordDto(jsonString);

        assertAll(
                () -> assertEquals(List.of("t 1 TEXT", "y 1 ONLY"), recordDto.getTokenGroups()),
                () -> assertEquals(LocalDateTime.parse("2023-08-29T19:04:24.892967"), recordDto.getTimestamp())
        );
    }

    @Test
    @org.junit.Test
    public void givenJsonStringWithNoTokenGroups_whenMappingJsonStringToResultRecordDto_thenEmptyCollectionOfTokenGroupsExpected() {
        String jsonString = "{\"formatted_result\":[],\"timestamp\":\"2023-08-29T20:22:57.980471\"}";
        ResultRecordDto recordDto = new ResultRecordDto(jsonString);

        assertAll(
                () -> assertEquals(List.of(), recordDto.getTokenGroups()),
                () -> assertEquals(LocalDateTime.parse("2023-08-29T20:22:57.980471"), recordDto.getTimestamp())
        );
    }
}