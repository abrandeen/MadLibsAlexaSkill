package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.highlow.MadLib;
import com.amazon.ask.highlow.WordTypeIntentHelper;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class ActorIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();

        // Can handle ActorIntent if currently playing a game and the given input was not yes or no
        return WordTypeIntentHelper.isCurrentlyPlaying(input)
                && input.matches(intentName("ActorIntent"))
                && !intentRequest.getIntent().getSlots().get("actor").getValue().equals("yes")
                && !intentRequest.getIntent().getSlots().get("actor").getValue().equals("no");
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();

        // If actor is the next type of word needed for the MadLib
        if (MadLib.getInstance().nextWordTypeEnum() == MadLib.WORD_TYPE.ACTOR) {
            // Store the actor
            MadLib.getInstance().wordGiven(intentRequest.getIntent().getSlots().get("actor").getValue());
            return WordTypeIntentHelper.correctWordTypeHandler(input);

        } else {
            // Next word needed is not an actor, reprompt for the correct type of word
            return WordTypeIntentHelper.wrongWordTypeHandler(input, MadLib.WORD_TYPE.ACTOR);
        }
    }
}
