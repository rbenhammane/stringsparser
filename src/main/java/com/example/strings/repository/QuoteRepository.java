package com.example.strings.repository;

import com.example.strings.domain.Quote;

import java.util.List;

/**
 * @author rbenhammane on 1/23/18.
 */
public interface QuoteRepository {

    List<Quote> findQuotes(String text);

}
