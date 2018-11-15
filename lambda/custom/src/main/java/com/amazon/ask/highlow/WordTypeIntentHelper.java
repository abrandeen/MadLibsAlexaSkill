package com.amazon.ask.highlow;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;


public class WordTypeIntentHelper {

    /**
     * Returns a boolean signifying if there is a MadLib game in progress
     * @param input -- input from the current intent given by the user
     * @return true if there is a MadLib game in progress, else false
     */
    public static boolean isCurrentlyPlaying (HandlerInput input){
        boolean isCurrentlyPlaying = false;
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        if (sessionAttributes.get("gameState") != null && sessionAttributes.get("gameState").equals("STARTED")) {
            isCurrentlyPlaying = true;
        }
        return isCurrentlyPlaying;
    }

    /**
     * Called when the user gives a response with the correct word type
     * If the MadLib is now complete, ends the game and reads the story
     * If the MadLib is not complete, prompts for the next word
     * @param input -- input from the current intent given by the user
     * @return the response the user should be given
     */
    public static Optional<Response> correctWordTypeHandler (HandlerInput input) {
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

    }

    /**
     * Handles the case when the user gives the wrong type of word by reprompting
     * for the correct word type.
     * @param input -- input from the current intent given by the user
     * @param givenType -- the incorrect type of word the user just gave
     * @return the formatted response to the user
     */
    public static Optional<Response> wrongWordTypeHandler (HandlerInput input, MadLib.WORD_TYPE givenType){
        return input.getResponseBuilder()
                .withSpeech("Sorry I'm not asking for a "
                        + MadLib.WORD_TYPE_STRING_MAP.get(givenType)
                        + " right now. Name a "
                        + MadLib.getInstance().nextWordTypeString())
                .withReprompt("Try saying a " + MadLib.getInstance().nextWordTypeString())
                .build();
    }
}
