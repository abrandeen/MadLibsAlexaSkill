package com.amazon.ask.highlow;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.highlow.handlers.*;

public class HighLowGameStreamHandler extends SkillStreamHandler {
    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                    new LaunchRequestHandler(),
                    new YesIntentHandler(),
                    new NoIntentHandler(),
                    new HelpIntentHandler(),
                    new ExitHandler(),
                    new SessionEndedRequestHandler(),
                    new FallbackIntentHandler(),
                    new CityIntentHandler(),
                    new StateIntentHandler(),
                    new UnsupportedRequestHandler())
                .addExceptionHandler(new ExceptionHandler())
                .withTableName("HighLowGame")
                .withAutoCreateTable(true)
                //adding skill
                .withSkillId("amzn1.ask.skill.bf8bd6c3-a165-4f0c-9e78-f649ef494ca1")
                .build();
    }

    public HighLowGameStreamHandler() { super(getSkill()); }
}
