package plavajs.questions.graphics.listener;

import plavajs.questions.graphics.QuestionsWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviousQuestionButtonActionListener implements ActionListener {
    private final QuestionsWindow questionsWindow;

    public PreviousQuestionButtonActionListener(QuestionsWindow questionsWindow) {
        this.questionsWindow = questionsWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        questionsWindow.getQuestionSet().decrementCurrentQuestionIndex();
        questionsWindow.getQuestionWindowService().activateNextPreviousButtons();
        questionsWindow.getQuestionWindowService().loadCurrentQuestion();
    }
}
