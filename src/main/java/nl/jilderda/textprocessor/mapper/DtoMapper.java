package nl.jilderda.textprocessor.mapper;

import lombok.experimental.UtilityClass;
import nl.jilderda.textprocessor.domain.model.WordFrequencyDto;
import nl.jilderda.textprocessor.to_implement.WordFrequency;


@UtilityClass
public class DtoMapper {
    public WordFrequencyDto mapWordFrequency(WordFrequency wordFrequency) {
        return WordFrequencyDto.builder()
                .word(wordFrequency.getWord())
                .frequency(wordFrequency.getFrequency())
                .build();

    }

}
