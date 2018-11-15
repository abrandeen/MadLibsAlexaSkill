package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.highlow.WordTypeIntentHelper;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NoIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return !WordTypeIntentHelper.isCurrentlyPlaying(input) && input.matches(intentName("AMAZON.NoIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        int endedSessionCount = (int) sessionAttributes.get("endedSessionCount") + 1;
        sessionAttributes.put("endedSessionCount", endedSessionCount);

        input.getAttributesManager().setPersistentAttributes(sessionAttributes);
        input.getAttributesManager().savePersistentAttributes();
        return input.getResponseBuilder()
                .withSpeech("Ok, see you next time!")
                .build();
    }

}
