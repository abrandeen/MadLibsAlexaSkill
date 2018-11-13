package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.highlow.MadLib;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

public class CityIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        boolean isCurrentlyPlaying = false;
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        if (sessionAttributes.get("gameState") != null && sessionAttributes.get("gameState").equals("STARTED")) {
            isCurrentlyPlaying = true;
        }

        return isCurrentlyPlaying && input.matches(intentName("USCityIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        // Get the MadLib for this game
        MadLib madLib = (MadLib) sessionAttributes.get("madLib");

        // If city is the next type of word needed for the MadLib
        if (madLib.nextWordTypeEnum() == MadLib.WORD_TYPE.CITY) {
            // Store the city
            madLib.wordGiven(intentRequest.getIntent().getSlots().get("uscity").getValue());
            sessionAttributes.put("madLib", madLib);

            // If the Mad Lib is complete
            if (madLib.nextWordTypeEnum() == MadLib.WORD_TYPE.NONE) {
                // Read the story and end the game
                int gamesPlayed = (int) sessionAttributes.get("gamesPlayed") + 1;
                sessionAttributes.put("gamesPlayed", gamesPlayed);
                sessionAttributes.put("gameState", "ENDED");

                input.getAttributesManager().setPersistentAttributes(sessionAttributes);
                input.getAttributesManager().savePersistentAttributes();

                return input.getResponseBuilder()
                        .withSpeech("All done! Here's your completed Mad Lib. " + madLib.createStory())
                        .withReprompt("Say yes to start a new game, or no to exit out of Mad Libs")
                        .build();

            // Mad Lib is not complete
            } else {
                // Prompt for the next word
                return input.getResponseBuilder()
                        .withSpeech("Name a " + madLib.nextWordTypeString())
                        .withReprompt("Try saying a " + madLib.nextWordTypeString())
                        .build();
            }

        // Next word needed is not a city, reprompt for the correct type of word
        } else {
            return input.getResponseBuilder()
                    .withSpeech("Sorry I'm not asking for a city right now. Name a " + madLib.nextWordTypeString())
                    .withReprompt("Try saying a " + madLib.nextWordTypeString())
                    .build();
        }
    }
}
