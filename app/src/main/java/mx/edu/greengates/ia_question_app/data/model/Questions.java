package mx.edu.greengates.ia_question_app.data.model;

import java.util.ArrayList;
import java.util.List;

import mx.edu.greengates.ia_question_app.data.model.Question;

public class Questions {

    private final List<Question> questions;

    public Questions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }
    public Question getQuestion(String identifier) {
        Question selectedQuestion = null;
        for(Question question: questions){
            if(question.getQuestion().compareTo(identifier) == 0){
                selectedQuestion = question;
                break;
            }
        }
        return selectedQuestion;
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public List<String> getAllTopics(){
        List<String> topics = new ArrayList<>();
        for( Question question: questions){
            if(!topics.contains(question.getId())){
                topics.add(question.getId());
            }
        }
        return topics;
    }

    public List<Question> getAllQuestionsByTopic(String topic){
        List<Question> topicQuestions = new ArrayList<>();
        for( Question question: questions){
            if((question.getId().compareTo(topic)==0)){
                topicQuestions.add(question);
            }
        }
        return topicQuestions;
    }
}