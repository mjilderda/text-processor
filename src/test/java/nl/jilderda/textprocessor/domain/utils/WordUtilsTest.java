package nl.jilderda.textprocessor.domain.utils;


import nl.jilderda.textprocessor.domain.model.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordUtilsTest {

    String regExLowerCase = "^[a-z]+$";

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("textArgumentStream")
    void getWordsfromStringShouldReturnAListWithAllCorrectWords(String name, String input, int arrayLength) {

        final List<Word> wordsfromString = WordUtils.getWordsFromString(input);

        wordsfromString.forEach(w ->
                assertTrue(w.getWord().matches(regExLowerCase))
        );

        assertEquals(arrayLength, wordsfromString.size());
    }

    @Test
    void getWordsfromStringShouldThrowExceptionWhenEmpty() {

        final Exception exception = assertThrows(IllegalArgumentException.class, () -> WordUtils.getWordsFromString(""));

        assertEquals("The supplied text is empty.", exception.getMessage());

    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("stringArgumentStream")
    void stringContainsOnlyAlphabeticChars(String name, String input, Boolean expectedBoolean) {

        final boolean returnBoolean = WordUtils.stringContainsOnlyAlphabeticChars(input);

        assertEquals(expectedBoolean, returnBoolean);
    }


    private static Stream<Arguments> textArgumentStream() {
        return Stream.of(
                Arguments.of("1. this.is.a.text.with.dots returns 6", "this.is.a.text.with.dots", 6),
                Arguments.of("2. this is a text with spaces returns 6", "this is a text with spaces", 6),
                Arguments.of("3. this\n is \n a\n text\n with\n enters returns 6", "this\n is \n a\n text\n with\n enters", 6),
                Arguments.of("4. this is a text with numbers in it r2d2 returns 8", "this is a text with numbers in it r2d2", 8),
                Arguments.of("5. this\tis\ta\ttext\twith\ttabs returns 6", "this\tis\ta\ttext\twith\ttabs", 6),
                Arguments.of("6. tHis is A tExt with upperCases returns 6", "tHis is A tExt with upperCases", 6)
        );
    }

    private static Stream<Arguments> stringArgumentStream() {
        return Stream.of(
                Arguments.of("1. all althabetic returns true", "Alphabetic", Boolean.TRUE),
                Arguments.of("2. With spaces returns false", "with spaces", Boolean.FALSE),
                Arguments.of("3. With underscores returns false", "with_underscore", Boolean.FALSE),
                Arguments.of("5. with numbers returns false", "R2D2", Boolean.FALSE)
        );
    }


}
