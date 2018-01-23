package com.example.strings.service.impl;

import com.example.strings.domain.Quote;
import com.example.strings.repository.QuoteRepository;
import com.example.strings.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rbenhammane on 1/23/18.
 */
@Service
public class QuoteServiceImpl implements QuoteService {

    private Logger logger = LoggerFactory.getLogger(QuoteServiceImpl.class);

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public List<Quote> findQuotes(String text) {

        List<Quote> quotes = quoteRepository.findQuotes(text);

        if (quotes.isEmpty()) {
            logger.debug("Not quote found in text: {}", text);
        } else {
            logger.debug("Successfully found {} quotes in text {}", quotes.size(), text);
        }

        return quotes;
    }
}
