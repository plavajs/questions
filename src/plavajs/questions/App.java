package plavajs.questions;

import plavajs.questions.graphics.IntroWindow;
import plavajs.questions.model.*;

public class App {
    public static void main(String[] args) {
        QuestionSet questionSet = new QuestionSet();

        IntroWindow introWindow = new IntroWindow(questionSet, true);
        introWindow.pack();
        introWindow.setVisible(true);
    }
}
