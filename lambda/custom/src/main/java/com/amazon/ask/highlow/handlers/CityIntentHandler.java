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

        // If city is the next type of word needed for the MadLib
        if (MadLib.getInstance().nextWordTypeEnum() == MadLib.WORD_TYPE.CITY) {
            // Store the city
            MadLib.getInstance().wordGiven(intentRequest.getIntent().getSlots().get("uscity").getValue());

            // If the Mad Lib is complete
            if (MadLib.getInstance().nextWordTypeEnum() == MadLib.WORD_TYPE.NONE) {
                // Read the story and end the game
                Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
                int gamesPlayed = (int) sessionAttributes.get("gamesPlayed") + 1;
                sessionAttributes.put("gamesPlayed", gamesPlayed);
                sessionAttributes.put("gameState", "ENDED");

                input.getAttributesManager().setPersistentAttributes(sessionAttributes);
                input.getAttributesManager().savePersistentAttributes();

                return input.getResponseBuilder()
                        .withSpeech("All done! Here's your completed Mad Lib. " + MadLib.getInstance().createStory())
                        .withReprompt("Say yes to start a new game, or no to exit out of Mad Libs")
                        .build();

            // Mad Lib is not complete
            } else {
                // Prompt for the next word
                return input.getResponseBuilder()
                        .withSpeech("Name a " + MadLib.getInstance().nextWordTypeString())
                        .withReprompt("Try saying a " + MadLib.getInstance().nextWordTypeString())
                        .build();
            }

        // Next word needed is not a city, reprompt for the correct type of word
        } else {
            return input.getResponseBuilder()
                    .withSpeech("Sorry I'm not asking for a city right now. Name a " + MadLib.getInstance().nextWordTypeString())
                    .withReprompt("Try saying a " + MadLib.getInstance().nextWordTypeString())
                    .build();
        }
    }
}
