package plavajs.questions.model;

import java.util.List;

public class Question {
    private Integer allQuestionsNumber;
    private String questionText;

    private List<Answer> answers;
    private AnswerMark correctAnswer;
    private boolean hasImage;

    public Integer getAllQuestionsNumber() {
        return allQuestionsNumber;
    }

    public void setAllQuestionsNumber(Integer allQuestionsNumber) {
        this.allQuestionsNumber = allQuestionsNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public AnswerMark getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(AnswerMark correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean hasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    @Override
    public String toString() {
        StringBuilder question = new StringBuilder(String.format("(%d.) %s", allQuestionsNumber, questionText));

        for (Answer answer : answers) {
            question.append(String.format("\n\t%s", answer.getText()));
        }

        return question.toString();
    }

}
