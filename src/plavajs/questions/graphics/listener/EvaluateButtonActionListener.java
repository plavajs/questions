package plavajs.questions.graphics.listener;

import plavajs.questions.graphics.QuestionsWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluateButtonActionListener implements ActionListener {

    private final QuestionsWindow questionsWindow;

    public EvaluateButtonActionListener(QuestionsWindow questionsWindow) {
        this.questionsWindow = questionsWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        questionsWindow.getQuestionWindowService().evaluate();
        questionsWindow.getQuestionWindowService().showMessageWindow(questionsWindow.getQuestionSet().getResult().getSuccessfulPercentage());
    }
}
