package nl.jilderda.textprocessor.domain.utils;

import lombok.experimental.UtilityClass;
import nl.jilderda.textprocessor.domain.model.Word;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class WordUtils {

    private static final List<String> UNWANTED_SUBSTRINGS = List.of(
            "\t", "\\t", // tab
            "\n", "\\n",  // return
            ",",
            "\"",
            "\\.",
            " "
    );

    private static final String FLAG = "%";

    public List<Word> getWordsFromString(String text) throws IllegalArgumentException {

        if (text.isEmpty()) throw new IllegalArgumentException("The supplied text is empty.");

        final String cleanerText = replaceUnwantedChars(text).toLowerCase();

        final List<String> Strings = Arrays.asList(cleanerText.split(FLAG));

        return Strings.stream()
                .distinct()
                .filter(WordUtils::stringContainsOnlyAlphabeticChars)
                .map(WordUtils::mapToWord)
                .collect(Collectors.toList());

    }

    private static String replaceUnwantedChars(String text) {
        String processedString = text;

        for (String unwantedSubString : UNWANTED_SUBSTRINGS) {
            processedString = processedString.replaceAll(unwantedSubString, FLAG);
        }
        processedString = processedString.replaceAll("%+", "%");

        return processedString;

    }

    public static boolean stringContainsOnlyAlphabeticChars(String checkWord) {
        checkWord = checkWord.trim();
        final char[] chars = checkWord.toCharArray();

        for (char aChar : chars) {
            if (!(Character.isLetter(aChar))) return false;
        }
        return true;
    }

    private static Word mapToWord(String s) {
        return new Word(s, 0);
    }

}
