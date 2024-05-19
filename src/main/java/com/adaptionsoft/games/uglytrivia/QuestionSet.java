package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionSet {

    public Map<QuestionType, List<Question>> questionsSet;


    public QuestionSet(Integer nbQuestions) {
        this.questionsSet = new HashMap<>();
        questionsSet.put(QuestionType.POP, new ArrayList<>());
        questionsSet.put(QuestionType.SCIENCE, new ArrayList<>());
        questionsSet.put(QuestionType.SPORTS, new ArrayList<>());
        questionsSet.put(QuestionType.ROCK, new ArrayList<>());
        for (int i = 0; i < nbQuestions; i++) {
            questionsSet.get(QuestionType.POP).add(new Question("Pop Question " + i));
            questionsSet.get(QuestionType.SCIENCE).add(new Question("Science Question " + i));
            questionsSet.get(QuestionType.SPORTS).add(new Question("Sports Question " + i));
            questionsSet.get(QuestionType.ROCK).add(new Question("Rock Question " + i));
        }
    }

    Question removeQuestionFor(QuestionType pop) {
        return questionsSet.get(pop).remove(0);
    }
}
