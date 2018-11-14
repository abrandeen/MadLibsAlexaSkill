package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.highlow.MadLib;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class YesIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        // only start a new game if yes is said when not playing a game.
        boolean isCurrentlyPlaying = false;
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        /* if game is currently playing then the game state should not be null (check to avoid nullptr)
           and the game state should also be 'started' */
        if (sessionAttributes.get("gameState") != null && sessionAttributes.get("gameState").equals("STARTED")) {
            isCurrentlyPlaying = true;
        }

        // check if not already playing and the user's input matches the YesIntent
        return !isCurrentlyPlaying && input.matches(intentName("AMAZON.YesIntent"));
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
                .withSpeech("Great! Name a " + MadLib.getInstance().nextWordTypeString())
                .withReprompt("Try saying a " + MadLib.getInstance().nextWordTypeString())
                .build();
    }

}
