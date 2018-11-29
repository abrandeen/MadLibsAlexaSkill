package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.highlow.MadLib;
import com.amazon.ask.highlow.WordTypeIntentHelper;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class YesIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {

        // Check if not already playing and the user's input matches the YesIntent
        return !WordTypeIntentHelper.isCurrentlyPlaying(input) && input.matches(intentName("AMAZON.YesIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put("gameState", "STARTED");
        input.getAttributesManager().setSessionAttributes(sessionAttributes);

        // Start the game with a new random MadLib
        MadLib.newMadLibRandom();

        return input.getResponseBuilder()
                // Ask the user for the first type of word in the MadLib
                .withSpeech("Great! Name a " + MadLib.getInstance().nextWordTypeString() + ".")
                .withReprompt("Try saying a " + MadLib.getInstance().nextWordTypeString() + ".")
                .build();
    }
}
