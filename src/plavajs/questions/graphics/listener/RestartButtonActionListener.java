package plavajs.questions.graphics.listener;

import plavajs.questions.graphics.IntroWindow;
import plavajs.questions.graphics.QuestionsWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButtonActionListener implements ActionListener {

    private final QuestionsWindow questionsWindow;

    public RestartButtonActionListener(QuestionsWindow questionsWindow) {
        this.questionsWindow = questionsWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IntroWindow introWindow = new IntroWindow(questionsWindow.getQuestionSet(), false);
        introWindow.pack();
        introWindow.setVisible(true);

        questionsWindow.getQuestionWindowService().restart();
        questionsWindow.getEvaluateButton().setEnabled(true);
        questionsWindow.getQuestionWindowService().activateNextPreviousButtons();
    }
}
