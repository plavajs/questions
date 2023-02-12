package plavajs.questions.graphics.listener;

import plavajs.questions.graphics.QuestionsWindow;
import plavajs.questions.service.FilesManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowResultsButtonActionListener implements ActionListener {

    private final QuestionsWindow questionsWindow;

    public ShowResultsButtonActionListener(QuestionsWindow questionsWindow) {
        this.questionsWindow = questionsWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        questionsWindow.getQuestionWindowService().showResultsWindow();
    }
}
