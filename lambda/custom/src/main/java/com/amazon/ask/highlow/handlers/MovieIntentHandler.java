package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.highlow.MadLib;
import com.amazon.ask.highlow.WordTypeIntentHelper;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazonaws.Request;

import java.util.Map;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

public class MovieIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {

        return WordTypeIntentHelper.isCurrentlyPlaying(input) && input.matches(intentName("MovieIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();

        // If movie is the next type of word needed for the MadLib
        if (MadLib.getInstance().nextWordTypeEnum() == MadLib.WORD_TYPE.MOVIE) {
            // Store the movie platform
            MadLib.getInstance().wordGiven(intentRequest.getIntent().getSlots().get("movie").getValue());
            return WordTypeIntentHelper.correctWordTypeHandler(input);

        } else {
            // Next word needed is not a movie, reprompt for the correct type of word
            return WordTypeIntentHelper.wrongWordTypeHandler(input, MadLib.WORD_TYPE.MOVIE);
        }
    }
}
