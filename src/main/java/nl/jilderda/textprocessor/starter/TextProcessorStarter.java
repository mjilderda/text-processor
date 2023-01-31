package nl.jilderda.textprocessor.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "nl.jilderda.textprocessor")
public class TextProcessorStarter {

    public static void main(String[] args) {
        SpringApplication.run(TextProcessorStarter.class, args);
    }
}
