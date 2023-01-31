package nl.jilderda.textprocessor.domain.service;

import nl.jilderda.textprocessor.domain.model.Word;
import nl.jilderda.textprocessor.domain.utils.WordUtils;
import nl.jilderda.textprocessor.to_implement.WordFrequency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;


@ExtendWith(MockitoExtension.class)
class WordFrequencyServiceTest {

    private List<Word> wordsList;

    private String inputText;

    @InjectMocks
    WordFrequencyService wordFrequencyService;

    @BeforeEach
    void setUp() {
        inputText = "one two two three three three four four four four five five five five five";

        wordsList = List.of(
                new Word("five", 5),
                new Word("four", 4),
                new Word("three", 3),
                new Word("two", 2),
                new Word("one", 1)
        );

    }

    @Test
    void calculateHighestFrequencyShouldReturnFrequencyOfFirstIndex() {
        try (MockedStatic<WordUtils> mocked = mockStatic(WordUtils.class)) {
            mocked.when(() -> WordUtils.getWordsFromString(anyString())).thenReturn(wordsList);
            mocked.when(() -> WordUtils.stringContainsOnlyAlphabeticChars(anyString())).thenReturn(Boolean.TRUE);

            final int value = wordFrequencyService.calculateHighestFrequency(inputText);

            assertEquals(wordsList.get(0).getFrequency(), value);

        }
    }

    @ParameterizedTest(name = "{index}: {0}")
    @CsvSource(
            {"the inputtext contains one ones, one, 1",
                    "the inputtext contains twice twice, two, 2",
                    "the inputtext contains three three times, three, 3",
                    "the inputtext contains three four times, four, 4",
                    "the inputtext contains three five times, five, 5",
                    "onetwo doesnot count for one and two, one, 1",
                    "This word is not found in the tekst, notfound,0"
            })
    void calculateFrequencyForWordShouldCountWords(String name, String word, String expectedCount) {
        final Integer value = wordFrequencyService.calculateFrequencyForWord(inputText, word);

        assertEquals(value, Integer.parseInt(expectedCount));
    }

    @ParameterizedTest(name = "{index}: {0}")
    @CsvSource(
            {"OutputSize equals input: 2 -> 2,  2, 2",
                    "OutputSize equals input: 3 -> 3, 3, 3",
                    "OutputSize equals input: 5 -> 5, 5, 5",
                    "OutputSize capped at listLength: 125 -> 5, 125, 5"
            })
    void calculateMostFrequentNWordsShouldReturnAListOfMaxN(String name, String n, String expectedCount) {
        try (MockedStatic<WordUtils> mocked = mockStatic(WordUtils.class)) {
            mocked.when(() -> WordUtils.getWordsFromString(anyString())).thenReturn(wordsList);
            mocked.when(() -> WordUtils.stringContainsOnlyAlphabeticChars(anyString())).thenReturn(Boolean.TRUE);

            final List<WordFrequency> wordFrequencies = wordFrequencyService.calculateMostFrequentNWords(inputText, Integer.parseInt(n));

            assertEquals(Integer.parseInt(expectedCount), wordFrequencies.size());

        }
    }
}