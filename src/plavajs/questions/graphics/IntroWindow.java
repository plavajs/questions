package plavajs.questions.graphics;

import plavajs.questions.model.QuestionSet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IntroWindow extends JDialog {
    private JPanel contentPane;
    private JSpinner questionCountSpinner;
    private JButton OKButton;
    private final QuestionSet questionSet;

    public IntroWindow(QuestionSet questionSet, boolean newWindow) {
        this.questionSet = questionSet;
        setupGraphics();
        setupActions(newWindow);
    }

    private void setupGraphics() {
        setContentPane(contentPane);
        setModal(true);
        setResizable(false);
        setBounds(800,400, 0, 0);
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1, 1, 823, 1);
        questionCountSpinner.setModel(spinnerNumberModel);
    }

    private void setupActions(boolean newWindow) {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupQuestionsCount(newWindow);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setupQuestionsCount(newWindow);
            }
        });
    }

    public void setupQuestionsCount(boolean newWindow) {
        int questionCount = (int)questionCountSpinner.getValue();
        questionSet.setSelectedQuestionsCount(questionCount);
        questionSet.setupQuestionSet();
        dispose();

        if (newWindow) {
            QuestionsWindow questionsWindow = new QuestionsWindow(questionSet);
            questionsWindow.pack();
            questionsWindow.setVisible(true);
        }
    }
}
