package com.amazon.ask.highlow.handlers;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;

import com.amazon.ask.highlow.handlers.CancelandStopIntentHandler;
import com.amazon.ask.highlow.handlers.HelloWorldIntentHandler;
import com.amazon.ask.highlow.handlers.HelpIntentHandler;
import com.amazon.ask.highlow.handlers.SessionEndedRequestHandler;
import com.amazon.ask.highlow.handlers.LaunchRequestHandler;

public class MadLibStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new CityIntentHandler(),
                        new StateIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler())
                .withSkillId("amzn1.ask.skill.bf8bd6c3-a165-4f0c-9e78-f649ef494ca1")
                .build();
    }

    public MadLibStreamHandler() {
        super(getSkill());
    }

}