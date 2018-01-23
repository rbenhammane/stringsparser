package com.example.strings.repository.file;

import com.example.strings.domain.Quote;
import com.example.strings.repository.QuoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a simple implementation that read the quotes file and store it in memory for future queries.
 *
 * @author rbenhammane on 1/23/18.
 */
@Repository
public class FlatFileQuoteRepository implements QuoteRepository {

    private static final String QUOTES_FILE_NAME = "quotes.txt";

    private Logger logger = LoggerFactory.getLogger(FlatFileQuoteRepository.class);

    private List<Quote> quotes;

    public FlatFileQuoteRepository() {

        // read the quotes file and populate the quotes list
        try {

            logger.info("Loading quotes from file {} ...", QUOTES_FILE_NAME);

            Resource resource = new ClassPathResource(QUOTES_FILE_NAME);
            FileReader fileReader = new FileReader(resource.getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            quotes = new ArrayList<>();

            String line = bufferedReader.readLine();

            while (line != null) {
                // in the given file the author and quote are separated by a tab
                String[] parts = line.split("\\t");
                if (parts.length == 2) {
                    quotes.add(new Quote(parts[0], parts[1]));
                    line = bufferedReader.readLine();
                }
            }

            logger.info("Successfully loaded {} quotes at startup", quotes.size());

        } catch (IOException e) {
            logger.warn("Failed loading quotes file: {}", e.getMessage());
            quotes = Collections.emptyList();
        }
    }

    @Override
    public List<Quote> findQuotes(String text) {

        logger.debug("Searching quotes in text: {}", text);

        List<Quote> result = this.quotes.stream()
                .filter(quote -> text.contains(quote.getText()))
                .collect(Collectors.toList());

        return result;
    }
}
