package plavajs.questions.graphics;

import plavajs.questions.graphics.listener.*;
import plavajs.questions.model.AnswerMark;
import plavajs.questions.model.QuestionSet;
import plavajs.questions.service.QuestionWindowService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuestionsWindow extends JDialog {

    private final QuestionWindowService questionWindowService;
    private JPanel contentPane;
    private JButton evaluateButton;
    private JButton restartButton;
    private JButton nextQuestionButton;
    private JButton previousQuestionButton;
    private JTextArea questionTextTextArea;
    private JTextArea answerATextArea;
    private JTextArea answerBTextArea;
    private JTextArea answerCTextArea;
    private JLabel questionNumberLabel;
    private JLabel imageLabel;
    private JButton answerAButton;
    private JButton answerBButton;
    private JButton answerCButton;
    private JLabel correctAnswersLabel;
    private JLabel wrongAnswersLabel;
    private JLabel successPercentageLabel;
    private JButton showResultsButton;
    private final QuestionSet questionSet;

    public QuestionsWindow(QuestionSet questionSet) {
        this.questionSet = questionSet;
        setupGraphics();
        setupActions();
        this.questionWindowService = new QuestionWindowService(this);
        questionWindowService.loadCurrentQuestion();
        previousQuestionButton.setEnabled(false);
        nextQuestionButton.setEnabled(false);
    }

    private void setupGraphics() {
        setContentPane(contentPane);
        this.setTitle("Martin je nejlepší...!!!");
        setModal(true);
        setBounds(200,100, 0, 0);
        setupTextArea(questionTextTextArea);
        setupTextArea(answerATextArea);
        setupTextArea(answerBTextArea);
        setupTextArea(answerCTextArea);
        correctAnswersLabel.setText(null);
        wrongAnswersLabel.setText(null);
        successPercentageLabel.setText(null);
    }

    private void setupTextArea(JTextArea textArea) {
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(contentPane.getBackground());
        textArea.setFont(textArea.getFont().deriveFont(Font.BOLD, 15));
    }

    private void setupActions() {
        answerAButton.addActionListener(new AnswerButtonActionListener(answerAButton, this, AnswerMark.A));
        answerBButton.addActionListener(new AnswerButtonActionListener(answerBButton, this, AnswerMark.B));
        answerCButton.addActionListener(new AnswerButtonActionListener(answerCButton, this, AnswerMark.C));
        previousQuestionButton.addActionListener(new PreviousQuestionButtonActionListener(this));
        nextQuestionButton.addActionListener(new NextQuestionButtonActionListener(this));
        restartButton.addActionListener(new RestartButtonActionListener(this));
        evaluateButton.addActionListener(new EvaluateButtonActionListener(this));
        showResultsButton.addActionListener(new ShowResultsButtonActionListener(this));

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });
    }

    private void exitApp() {
        dispose();
    }

    public QuestionWindowService getQuestionWindowService() {
        return questionWindowService;
    }

    public JButton getEvaluateButton() {
        return evaluateButton;
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public JButton getNextQuestionButton() {
        return nextQuestionButton;
    }

    public JButton getPreviousQuestionButton() {
        return previousQuestionButton;
    }

    public JTextArea getQuestionTextTextArea() {
        return questionTextTextArea;
    }

    public JTextArea getAnswerATextArea() {
        return answerATextArea;
    }

    public JTextArea getAnswerBTextArea() {
        return answerBTextArea;
    }

    public JTextArea getAnswerCTextArea() {
        return answerCTextArea;
    }

    public JLabel getQuestionNumberLabel() {
        return questionNumberLabel;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public JButton getAnswerAButton() {
        return answerAButton;
    }

    public JButton getAnswerBButton() {
        return answerBButton;
    }

    public JButton getAnswerCButton() {
        return answerCButton;
    }

    public QuestionSet getQuestionSet() {
        return questionSet;
    }

    public JLabel getCorrectAnswersLabel() {
        return correctAnswersLabel;
    }

    public JLabel getWrongAnswersLabel() {
        return wrongAnswersLabel;
    }

    public JLabel getSuccessPercentageLabel() {
        return successPercentageLabel;
    }
}