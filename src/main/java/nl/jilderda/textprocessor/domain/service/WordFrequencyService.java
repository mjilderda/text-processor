package nl.jilderda.textprocessor.domain.service;

import lombok.extern.slf4j.Slf4j;
import nl.jilderda.textprocessor.domain.model.Word;
import nl.jilderda.textprocessor.domain.utils.WordUtils;
import nl.jilderda.textprocessor.to_implement.WordFrequency;
import nl.jilderda.textprocessor.to_implement.WordFrequencyAnalyzer;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
public class WordFrequencyService implements WordFrequencyAnalyzer {

    @Override
    public int calculateHighestFrequency(String text) throws IllegalArgumentException {

        final List<Word> lowerCasedWordListWithFrequency = getOrderedLowerCasedWordListWithFrequency(text);

        return lowerCasedWordListWithFrequency.get(0).getFrequency();
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) throws IllegalArgumentException {
        int count = 0;
        final Pattern searchWord = Pattern.compile("\\b" + word.toLowerCase() + "\\b");
        final Matcher textMatcher = searchWord.matcher(text);

        if (!(WordUtils.stringContainsOnlyAlphabeticChars(word)))
            throw new IllegalArgumentException("the word \"" + word + "\" contains unwanted characters.");

        while (textMatcher.find()) {
            count++;
        }
        return count;
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) throws IllegalArgumentException {

        final List<Word> lowerCasedWordListWithFrequency = getOrderedLowerCasedWordListWithFrequency(text);

        return lowerCasedWordListWithFrequency.stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    private List<Word> getOrderedLowerCasedWordListWithFrequency(String text) throws IllegalArgumentException {
        final String lowerCasedText = text.toLowerCase();

        final List<Word> wordsFromString = WordUtils.getWordsFromString(lowerCasedText);

        if (wordsFromString.isEmpty()) throw new IllegalArgumentException("The supplied text is incorrect.");

        for (Word word : wordsFromString) {
            word.setFrequency(calculateFrequencyForWord(lowerCasedText, word.getWord()));
        }

        return wordsFromString.stream()
                .sorted(
                        Comparator.comparing(WordFrequency::getFrequency)
                                .reversed()
                                .thenComparing(WordFrequency::getWord))
                .collect(Collectors.toList());
    }

}
