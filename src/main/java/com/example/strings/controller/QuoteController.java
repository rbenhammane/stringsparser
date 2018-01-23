package com.example.strings.controller;

import com.example.strings.domain.Quote;
import com.example.strings.service.QuoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rbenhammane on 1/23/18.
 */
@RestController
@RequestMapping("/quote")
@Api(value = "Quote", description = "Quotes operations")
public class QuoteController {

    private Logger logger = LoggerFactory.getLogger(QuoteController.class);

    @Autowired
    private QuoteService quoteService;

    @ApiOperation("Search for quotes inside the given text")
    @ApiResponses({
            @ApiResponse(code = 404, message = "No quote found in the given text!")
    })
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<Quote>> searchQuotes(String text) {

        logger.debug("New request for quotes in text: {}", text);

        ResponseEntity<List<Quote>> response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        List<Quote> quotes = quoteService.findQuotes(text);

        if (quotes != null && !quotes.isEmpty()) {
            response = ResponseEntity.ok(quotes);
        }

        return response;
    }
}
