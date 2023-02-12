package plavajs.questions.dto;

import java.util.List;

public class ResultDto {
    private String date;
    private int totalQuestions;
    private int correctAnswersCount;
    private int wrongAnswersCount;
    private double successPercentage;
    private List<QuestionResultDto> questions;

    public ResultDto() {}

    public ResultDto(String date, int totalQuestions, int correctAnswersCount, int wrongAnswersCount, double successPercentage,
                     List<QuestionResultDto> questions) {
        this.date = date;
        this.totalQuestions = totalQuestions;
        this.correctAnswersCount = correctAnswersCount;
        this.wrongAnswersCount = wrongAnswersCount;
        this.successPercentage = successPercentage;
        this.questions = questions;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public void setCorrectAnswersCount(int correctAnswersCount) {
        this.correctAnswersCount = correctAnswersCount;
    }

    public int getWrongAnswersCount() {
        return wrongAnswersCount;
    }

    public void setWrongAnswersCount(int wrongAnswersCount) {
        this.wrongAnswersCount = wrongAnswersCount;
    }

    public double getSuccessPercentage() {
        return successPercentage;
    }

    public void setSuccessPercentage(double successPercentage) {
        this.successPercentage = successPercentage;
    }

    public List<QuestionResultDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResultDto> questions) {
        this.questions = questions;
    }
}
