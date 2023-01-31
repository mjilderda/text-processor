package nl.jilderda.textprocessor.rest.api.v1.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.jilderda.textprocessor.domain.model.*;
import nl.jilderda.textprocessor.domain.service.WordFrequencyService;
import nl.jilderda.textprocessor.mapper.DtoMapper;
import nl.jilderda.textprocessor.rest.api.v1.TextProcessorApiDelegate;
import nl.jilderda.textprocessor.to_implement.WordFrequency;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TextProcessorController implements TextProcessorApiDelegate {

    private WordFrequencyService wordFrequencyService;

    @Override
    public ResponseEntity<FrequencyDto> calculateHighest(CalculateHighestDto calculateHighestDto) {
        log.info("Request received on highest-endpoint");

        final int frequency = wordFrequencyService.calculateHighestFrequency(
                calculateHighestDto.getText());

        return ResponseEntity.ok(
                FrequencyDto.builder()
                        .frequency(frequency)
                        .build()
        );
    }

    @Override
    public ResponseEntity<FrequencyDto> calculateWordFrequency(CalculateFrequencyDto calculateFrequencyDto) throws IllegalArgumentException {
        log.info("Request received on frequency-endpoint");

        final int frequency = wordFrequencyService.calculateFrequencyForWord(
                calculateFrequencyDto.getText(), calculateFrequencyDto.getWord());

        return ResponseEntity.ok(
                FrequencyDto.builder()
                        .frequency(frequency)
                        .build()
        );
    }

    @Override
    public ResponseEntity<List<WordFrequencyDto>> calculateMostFrequent(CalculateMostFrequentDto calculateMostFrequentDto) {
        log.info("Request received on mostFrequentNwords-endpoint");
        final List<WordFrequency> wordFrequencies = wordFrequencyService
                .calculateMostFrequentNWords(calculateMostFrequentDto.getText(), calculateMostFrequentDto.getNumberOfWords());

        return ResponseEntity.ok(
                wordFrequencies.stream()
                        .map(DtoMapper::mapWordFrequency)
                        .collect(Collectors.toList())
        );
    }

}
