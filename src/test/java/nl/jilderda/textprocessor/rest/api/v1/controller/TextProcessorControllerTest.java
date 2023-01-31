package nl.jilderda.textprocessor.rest.api.v1.controller;

import nl.jilderda.textprocessor.domain.model.*;
import nl.jilderda.textprocessor.domain.service.WordFrequencyService;
import nl.jilderda.textprocessor.to_implement.WordFrequency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TextProcessorControllerTest {

    @Mock
    private WordFrequencyService wordFrequencyService;

    @InjectMocks
    TextProcessorController textProcessorController;

    private List<WordFrequency> wordsList;

    private CalculateMostFrequentDto calculateMostFrequentDto;

    private CalculateFrequencyDto calculateFrequencyDto;

    private CalculateHighestDto calculateHighestDto;

    @BeforeEach
    void setUp() {
        wordsList = List.of(
                new Word("wordOne", 1),
                new Word("wordTwo", 2),
                new Word("wordThree", 3)
        );

        calculateMostFrequentDto = CalculateMostFrequentDto.builder()
                .text("This is a text.")
                .numberOfWords(12)
                .build();

        calculateFrequencyDto = CalculateFrequencyDto.builder()
                .text("This is a text.")
                .word("is")
                .build();

        calculateHighestDto = CalculateHighestDto.builder()
                .text("This is a text.")
                .build();
    }

    @Test
    void calculateHighestShouldCallCalculateHighestFrequencyMethod() {
        when(wordFrequencyService.calculateHighestFrequency(any())).thenReturn(123);

        textProcessorController.calculateHighest(calculateHighestDto);

        verify(wordFrequencyService).calculateHighestFrequency(any());
    }

    @Test
    void calculateMostFrequentShouldCallCalculateMostFrequentNWordsMethod() {
        when(wordFrequencyService.calculateMostFrequentNWords(any(), anyInt())).thenReturn(wordsList);

        textProcessorController.calculateMostFrequent(calculateMostFrequentDto);

        verify(wordFrequencyService).calculateMostFrequentNWords(any(), anyInt());
    }

    @Test
    void calculateWordFrequencyShouldCallCalculateFrequencyForWordMethod() {
        when(wordFrequencyService.calculateFrequencyForWord(any(), any())).thenReturn(123);

        textProcessorController.calculateWordFrequency(calculateFrequencyDto);

        verify(wordFrequencyService).calculateFrequencyForWord(any(), any());
    }

    @Test
    void calculateHighestStatusCodeShouldBe200() {
        when(wordFrequencyService.calculateHighestFrequency(any())).thenReturn(1);

        final ResponseEntity<FrequencyDto> responseEntity = textProcessorController.calculateHighest(calculateHighestDto);

        assertThat(responseEntity.getStatusCode().value(), is(200));
        assertNotNull(responseEntity.getBody());

    }


    @Test
    void calculateMostFrequentStatusCodeShouldBe200() {
        when(wordFrequencyService.calculateMostFrequentNWords(any(), anyInt())).thenReturn(wordsList);

        final ResponseEntity<List<WordFrequencyDto>> responseEntity = textProcessorController.calculateMostFrequent(calculateMostFrequentDto);

        assertThat(responseEntity.getStatusCode().value(), is(200));
        assertNotNull(responseEntity.getBody());

    }

    @Test
    void calculateWordFrequencyStatusCodeShouldBe200() {

        when(wordFrequencyService.calculateFrequencyForWord(any(), any())).thenReturn(123);

        final ResponseEntity<FrequencyDto> responseEntity = textProcessorController.calculateWordFrequency(calculateFrequencyDto);

        assertThat(responseEntity.getStatusCode().value(), is(200));
        assertNotNull(responseEntity.getBody());
    }

}