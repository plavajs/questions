package plavajs.questions.graphics.listener;

import plavajs.questions.graphics.QuestionsWindow;
import plavajs.questions.model.AnswerMark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnswerButtonActionListener implements ActionListener {

    private final JButton chosenAnswerButton;
    private final QuestionsWindow questionsWindow;
    private final AnswerMark answerMark;

    public AnswerButtonActionListener(JButton chosenAnswerButton, QuestionsWindow questionsWindow, AnswerMark answerMark) {
        this.chosenAnswerButton = chosenAnswerButton;
        this.questionsWindow = questionsWindow;
        this.answerMark = answerMark;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        unmarkAllButtons();
        chosenAnswerButton.setBackground(Color.CYAN);
        questionsWindow.getQuestionSet().getCurrentAnsweredQuestion().setChosenAnswer(answerMark);
        questionsWindow.getQuestionWindowService().activateNextPreviousButtons();
    }

    private void unmarkAllButtons() {
        Color neutral = new JButton().getBackground();
        questionsWindow.getAnswerAButton().setBackground(neutral);
        questionsWindow.getAnswerBButton().setBackground(neutral);
        questionsWindow.getAnswerCButton().setBackground(neutral);
    }
}
