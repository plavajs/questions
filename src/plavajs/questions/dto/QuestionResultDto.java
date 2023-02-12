package plavajs.questions.dto;

import plavajs.questions.model.AnswerMark;

public class QuestionResultDto {
    private int allQuestionsNumber;
    private int selectedQuestionsNumber;
    private boolean answeredCorrectly;
    private AnswerMark correctAnswer;
    private AnswerMark chosenAnswer;

    public QuestionResultDto() {}

    public QuestionResultDto(int allQuestionsNumber, int selectedQuestionsNumber, boolean answeredCorrectly,
            AnswerMark correctAnswer, AnswerMark chosenAnswer) {
        this.allQuestionsNumber = allQuestionsNumber;
        this.selectedQuestionsNumber = selectedQuestionsNumber;
        this.answeredCorrectly = answeredCorrectly;
        this.correctAnswer = correctAnswer;
        this.chosenAnswer = chosenAnswer;
    }

    public int getAllQuestionsNumber() {
        return allQuestionsNumber;
    }

    public void setAllQuestionsNumber(int allQuestionsNumber) {
        this.allQuestionsNumber = allQuestionsNumber;
    }

    public int getSelectedQuestionsNumber() {
        return selectedQuestionsNumber;
    }

    public void setSelectedQuestionsNumber(int selectedQuestionsNumber) {
        this.selectedQuestionsNumber = selectedQuestionsNumber;
    }

    public boolean isAnsweredCorrectly() {
        return answeredCorrectly;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }

    public AnswerMark getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(AnswerMark correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public AnswerMark getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(AnswerMark chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
}
