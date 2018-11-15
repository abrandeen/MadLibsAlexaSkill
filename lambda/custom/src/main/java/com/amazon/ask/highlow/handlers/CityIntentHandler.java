package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.highlow.MadLib;
import com.amazon.ask.highlow.WordTypeIntentHelper;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

public class CityIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {

        return WordTypeIntentHelper.isCurrentlyPlaying(input) && input.matches(intentName("USCityIntent"))
                && !input.matches(intentName("AMAZON.YesIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();

        // If city is the next type of word needed for the MadLib
        if (MadLib.getInstance().nextWordTypeEnum() == MadLib.WORD_TYPE.CITY) {
            // Store the city
            MadLib.getInstance().wordGiven(intentRequest.getIntent().getSlots().get("uscity").getValue());
            return WordTypeIntentHelper.correctWordTypeHandler(input);

        } else {
            // Next word needed is not a city, reprompt for the correct type of word
            return input.getResponseBuilder()
                    .withSpeech("Sorry I'm not asking for a city right now. Name a " + MadLib.getInstance().nextWordTypeString())
                    .withReprompt("Try saying a " + MadLib.getInstance().nextWordTypeString())
                    .build();
        }
    }
}
