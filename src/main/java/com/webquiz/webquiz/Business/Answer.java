package com.webquiz.webquiz.Business;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    private final List<Integer> answer;

    public Answer(){
        answer = null;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public Answer(ArrayList<Integer> answer) {
        this.answer = answer;
    }
}
