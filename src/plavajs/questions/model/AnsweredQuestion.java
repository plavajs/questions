package plavajs.questions.model;

public class AnsweredQuestion {
    private final Question question;
    private AnswerMark chosenAnswer;

    public AnsweredQuestion(Question question) {
        this.question = question;
    }

    public AnsweredQuestion(Question question, AnswerMark chosenAnswer) {
        this.question = question;
        this.chosenAnswer = chosenAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public AnswerMark getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(AnswerMark chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    public boolean isAnsweredCorrectly() {
        return chosenAnswer != null && chosenAnswer == question.getCorrectAnswer();
    }
}
