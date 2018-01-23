package com.example.strings.service;

import com.example.strings.domain.Quote;

import java.util.List;

/**
 * @author rbenhammane on 1/23/18.
 */
public interface QuoteService {

    List<Quote> findQuotes(String text);
}
