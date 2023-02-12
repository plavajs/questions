package plavajs.questions.model;

public class Answer {
    private String text;
    private boolean correct;
    private AnswerMark answerMark;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public AnswerMark getAnswerMark() {
        return answerMark;
    }

    public void setAnswerMark(AnswerMark answerMark) {
        this.answerMark = answerMark;
    }
}
