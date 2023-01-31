package nl.jilderda.textprocessor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Setter;
import nl.jilderda.textprocessor.to_implement.WordFrequency;

@AllArgsConstructor
@Setter
public class Word implements WordFrequency {

    private String word;

    private int frequency;

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }
}
