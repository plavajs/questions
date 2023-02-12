package plavajs.questions.service;

import plavajs.questions.graphics.MessageWindow;
import plavajs.questions.graphics.QuestionsWindow;
import plavajs.questions.graphics.ResultsWindow;
import plavajs.questions.model.AnswerMark;
import plavajs.questions.model.AnsweredQuestion;
import plavajs.questions.model.Question;
import plavajs.questions.model.QuestionSet;
import plavajs.questions.model.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class QuestionWindowService {

    private final QuestionsWindow questionsWindow;
    private final QuestionSet questionSet;

    private final List<String> excellentResultSentences = List.of(
            "WOW! Už se raději neuč, abys to náhodou neuměl líp než učitel...",
            "Neuvěřitelný! 100% a to jsi určitě ani nepodváděl...",
            "Excelentní! Konečně 100% na 6891679791. pokus...",
            "Hustě ty! Jsi spolkl encyklopedii?!"
    );
    private final List<String> greatResultSentences = List.of(
            "Úžasný výsledek! Ale pořád to není 100%...",
            "Paráda! Ale do 100% ještě kousek chybí...",
            "Hmmmmm! Ještě pár procent a máš 100...",
            "Dost dobrý! Už chybí jen pár % do 100..."
    );

    private final List<String> middleResultSentences = List.of(
            "Hm, na titul to ještě není. Ale to doženeš!",
            "No, nějaký progres tam je. Ještě zaber!",
            "Ještě to bude chtít vypilovat. Držím palce!",
            "Snaha se cenní, ale na úspěch bude potřeba ještě přitlačit!"
    );

    private final List<String> badResultSentences = List.of(
            "Už jenom 89794616949 hodinek učení a bude to úspěsný pokus...",
            "To je teda bída...",
            "Jsi se moc neučil viď...",
            "Takhle špatně to zvládne natipovat i moje kočka..."
    );

    public QuestionWindowService(QuestionsWindow questionsWindow) {
        this.questionsWindow = questionsWindow;
        this.questionSet = questionsWindow.getQuestionSet();
    }

    public void activateNextPreviousButtons() {
        JButton previous = questionsWindow.getPreviousQuestionButton();
        JButton next = questionsWindow.getNextQuestionButton();
        previous.setEnabled(false);
        next.setEnabled(false);
        if (questionSet.getCurrentQuestionIndex() > 0) {
            previous.setEnabled(true);
        }

        if (questionSet.getCurrentQuestionIndex() < questionSet.getSelectedQuestionsCount() - 1) {
            if (questionSet.getCurrentAnsweredQuestion().getChosenAnswer() != null || questionSet.isEvaluated()) {
                next.setEnabled(true);
            }
        }
    }

    public void loadCurrentQuestion() {
        JButton answerAButton = questionsWindow.getAnswerAButton();
        JButton answerBButton = questionsWindow.getAnswerBButton();
        JButton answerCButton = questionsWindow.getAnswerCButton();

        answerAButton.setEnabled(true);
        answerBButton.setEnabled(true);
        answerCButton.setEnabled(true);

        AnsweredQuestion currentAnsweredQuestion = questionSet.getCurrentAnsweredQuestion();
        Question currentQuestion = questionSet.getCurrentAnsweredQuestion().getQuestion();
        questionsWindow.getQuestionTextTextArea().setText(String.format("(%d) %s", currentQuestion.getAllQuestionsNumber(), currentQuestion.getQuestionText()));
        questionsWindow.getAnswerATextArea().setText(String.format("%s", currentQuestion.getAnswers().get(0).getText()));
        questionsWindow.getAnswerBTextArea().setText(String.format("%s", currentQuestion.getAnswers().get(1).getText()));
        questionsWindow.getAnswerCTextArea().setText(String.format("%s", currentQuestion.getAnswers().get(2).getText()));
        questionsWindow.getQuestionNumberLabel().setText(String.format("Otázka č. %d z %d", questionSet.getCurrentQuestionIndex() + 1, questionSet.getSelectedQuestionsCount()));

        if (currentQuestion.hasImage()) {
            BufferedImage image = questionSet.getCurrentQuestionImage();
            questionsWindow.getImageLabel().setIcon(new ImageIcon(image));
        } else {
            questionsWindow.getImageLabel().setIcon(null);
        }

        unmarkAnswers();
        AnswerMark chosenAnswer = currentAnsweredQuestion.getChosenAnswer();
        if (questionSet.isEvaluated()) {
            loadEvaluatedAnswer();
        } else {
            loadMarkedAnswer(chosenAnswer);
        }
    }

    private void unmarkAnswers() {
        Color neutral = new JButton().getBackground();
        JButton answerAButton = questionsWindow.getAnswerAButton();
        JButton answerBButton = questionsWindow.getAnswerBButton();
        JButton answerCButton = questionsWindow.getAnswerCButton();

        answerAButton.setBackground(neutral);
        answerBButton.setBackground(neutral);
        answerCButton.setBackground(neutral);
    }

    private void loadEvaluatedAnswer() {
        JButton answerAButton = questionsWindow.getAnswerAButton();
        JButton answerBButton = questionsWindow.getAnswerBButton();
        JButton answerCButton = questionsWindow.getAnswerCButton();

        answerAButton.setEnabled(false);
        answerBButton.setEnabled(false);
        answerCButton.setEnabled(false);

        AnswerMark chosenAnswer = questionSet.getCurrentAnsweredQuestion().getChosenAnswer();
        AnswerMark correctAnswer = questionSet.getCurrentAnsweredQuestion().getQuestion().getCorrectAnswer();
        if (chosenAnswer == null) {
            colorAnswerButton(correctAnswer, Color.red);
        } else if (chosenAnswer == correctAnswer) {
            colorAnswerButton(chosenAnswer, Color.green);
        } else {
            colorAnswerButton(chosenAnswer, Color.red);
            colorAnswerButton(correctAnswer, Color.orange);
        }
    }

    private void loadMarkedAnswer(AnswerMark chosenAnswer) {
        if (chosenAnswer != null) {
            colorAnswerButton(chosenAnswer, Color.CYAN);
        }
    }

    private void colorAnswerButton(AnswerMark answer, Color color) {
        JButton answerAButton = questionsWindow.getAnswerAButton();
        JButton answerBButton = questionsWindow.getAnswerBButton();
        JButton answerCButton = questionsWindow.getAnswerCButton();

        switch (answer) {
            case A: answerAButton.setBackground(color); break;
            case B: answerBButton.setBackground(color); break;
            case C: answerCButton.setBackground(color); break;
        }
    }

    public void evaluate() {
        questionSet.evaluateQuestionSet();
        questionsWindow.getEvaluateButton().setEnabled(false);
        loadCurrentQuestion();
        activateNextPreviousButtons();
        Result result = questionSet.getResult();
        questionsWindow.getCorrectAnswersLabel().setText(String.format("Správně: %d odpovědí.", result.getCorrectAnswersCount()));
        questionsWindow.getWrongAnswersLabel().setText(String.format("Chybně: %d odpovědí.", result.getWrongAnswersCount()));
        questionsWindow.getSuccessPercentageLabel().setText(String.format("Úspěšnost: %.1f%%", result.getSuccessfulPercentage()));
    }

    public void restart() {
        questionSet.restart();
        questionsWindow.getCorrectAnswersLabel().setText(null);
        questionsWindow.getWrongAnswersLabel().setText(null);
        questionsWindow.getSuccessPercentageLabel().setText(null);
        loadCurrentQuestion();
    }

    public void showResultsWindow() {
        ResultsWindow resultsWindow = new ResultsWindow(questionSet.getResultService());
        resultsWindow.pack();
        resultsWindow.setVisible(true);
    }

    public void showMessageWindow(double percentage) {
        String message = "";
        Random random = new Random();
        if (percentage < 50) {
            int messagesListSize = badResultSentences.size();
            int index = random.nextInt(messagesListSize);
            message = badResultSentences.get(index);
        } else if (percentage < 80) {
            int messagesListSize = middleResultSentences.size();
            int index = random.nextInt(messagesListSize);
            message = middleResultSentences.get(index);
        } else if (percentage < 100) {
            int messagesListSize = greatResultSentences.size();
            int index = random.nextInt(messagesListSize);
            message = greatResultSentences.get(index);
        } else if (percentage == 100) {
            int messagesListSize = excellentResultSentences.size();
            int index = random.nextInt(messagesListSize);
            message = excellentResultSentences.get(index);
        }
        MessageWindow messageWindow = new MessageWindow(message);
        messageWindow.pack();
        messageWindow.setVisible(true);
    }
}
