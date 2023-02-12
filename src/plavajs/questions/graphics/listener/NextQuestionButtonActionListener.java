package plavajs.questions.graphics.listener;

import plavajs.questions.graphics.QuestionsWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextQuestionButtonActionListener implements ActionListener {

    private final QuestionsWindow questionsWindow;

    public NextQuestionButtonActionListener(QuestionsWindow questionsWindow) {
        this.questionsWindow = questionsWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        questionsWindow.getQuestionSet().incrementCurrentQuestionIndex();
        questionsWindow.getQuestionWindowService().activateNextPreviousButtons();
        questionsWindow.getQuestionWindowService().loadCurrentQuestion();
    }
}
