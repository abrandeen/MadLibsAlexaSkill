package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
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

        // Do not need these, fix to take user's input as words
        Double randomNumber = Math.floor(Math.random() * 101);
        sessionAttributes.put("guessNumber", randomNumber.intValue());


        input.getAttributesManager().setSessionAttributes(sessionAttributes);

        return input.getResponseBuilder()
                // Udpate these sayings to match the game
                .withSpeech("Great! Try saying a number to start the game.")
                .withReprompt("Try saying a number")
                .build();
    }

}
