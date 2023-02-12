package plavajs.questions.model;

public class Result {
    private final QuestionSet questionSet;
    private final int correctAnswersCount;
    private final int wrongAnswersCount;
    private final double successfulPercentage;

    public Result(QuestionSet questionSet, int correctAnswersCount, int wrongAnswersCount, double successfulPercentage) {
        this.questionSet = questionSet;
        this.correctAnswersCount = correctAnswersCount;
        this.wrongAnswersCount = wrongAnswersCount;
        this.successfulPercentage = successfulPercentage;
    }

    public QuestionSet getQuestionSet() {
        return questionSet;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public int getWrongAnswersCount() {
        return wrongAnswersCount;
    }

    public double getSuccessfulPercentage() {
        return successfulPercentage;
    }
}
