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

public class TopicIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {

        // Check if not already playing and the user's input matches the TopicIntent
        return !WordTypeIntentHelper.isCurrentlyPlaying(input) && input.matches(intentName("TopicIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        String topic = intentRequest.getIntent().getSlots().get("topic").getValue();

        // If topic is not valid
        if (MadLib.TOPIC_STRING_MAP.get(topic) == null){
            return input.getResponseBuilder()
                    // Let the user know what the valid topics are
                    .withSpeech("Sorry, I don't have any Mad Libs about " + topic
                            + ". I have Mad Libs about " + String.join(", ", MadLib.TOPIC_STRING_MAP.keySet())
                            + ". Try again with one of those topics.")
                    .withReprompt("Do you want to play Mad Libs?")
                    .build();
        }

        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put("gameState", "STARTED");
        input.getAttributesManager().setSessionAttributes(sessionAttributes);

        // Start the game with a new MadLib from the specified topic
        MadLib.newMadLibTopic(MadLib.TOPIC_STRING_MAP.get(topic));

        return input.getResponseBuilder()
                // Ask the user for the first type of word in the MadLib
                .withSpeech("Wooo! Let's play a Mad Lib about " + topic
                        + ". Name a " + MadLib.getInstance().nextWordTypeString() + ".")
                .withReprompt("Try saying a " + MadLib.getInstance().nextWordTypeString() + ".")
                .build();
    }
}
