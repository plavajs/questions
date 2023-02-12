package plavajs.questions.model;

public enum AnswerMark {

    A(1, 'a'),
    B(2, 'b'),
    C(3, 'c'),
    ;

    private final int answerNumber;
    private final char answerChar;
    AnswerMark(int answerNumber, char answerChar) {
        this.answerNumber = answerNumber;
        this.answerChar = answerChar;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public char getAnswerChar() {
        return answerChar;
    }

    public AnswerMark getByAnswerCharOrNull(char answerChar) {
        for (AnswerMark mark : AnswerMark.values()) {
            if (answerChar == mark.getAnswerChar()) {
                return mark;
            }
        }

        return null;
    }

    public static AnswerMark getByAnswerNumberOrThrow(int answerNumber) {
        for (AnswerMark mark : AnswerMark.values()) {
            if (answerNumber == mark.getAnswerNumber()) {
                return mark;
            }
        }

        throw new RuntimeException();
    }
}
