package nl.jilderda.textprocessor.to_implement;

import java.util.List;

public interface WordFrequencyAnalyzer {

    int calculateHighestFrequency(String text);

    int calculateFrequencyForWord(String text, String word);

    List<WordFrequency> calculateMostFrequentNWords(String text, int n);
}
