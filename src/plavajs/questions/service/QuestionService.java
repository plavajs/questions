package plavajs.questions.service;

import plavajs.questions.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class QuestionService {

    private final FilesManager filesManager;
    private final List<Question> allQuestions;

    public QuestionService(FilesManager filesManager) {
        this.filesManager = filesManager;
        this.allQuestions = loadAllQuestions();
    }

    private List<Question> loadAllQuestions() {
        return filesManager.loadAllQuestionsResource();
    }

    public Map<Integer, AnsweredQuestion> pickListOfRandomQuestions(int questionsNeeded) {
        Set<Integer> selectedQuestionsIndexes = new HashSet<>();
        int allQuestionsSize = allQuestions.size();
        Random random = new Random();
        while (selectedQuestionsIndexes.size() < questionsNeeded) {
            Integer index = random.nextInt(allQuestionsSize);
            selectedQuestionsIndexes.add(index);
        }

        List<Question> questions = selectedQuestionsIndexes.stream()
                .map(allQuestions::get)
                .peek(this::shuffleQuestionAnswers)
                .collect(Collectors.toList());
        List<AnsweredQuestion> answeredQuestions = questions.stream().map(AnsweredQuestion::new).collect(Collectors.toList());
        return answeredQuestions.stream().collect(Collectors.toMap(answeredQuestions::indexOf, question -> question));
    }

    private void shuffleQuestionAnswers(Question question) {
        AnswerMark correctAnswerMark = question.getCorrectAnswer();
        List<Answer> answers = question.getAnswers();
        Collections.shuffle(answers);

        int answersSize = answers.size();
        for (int i = 0; i < answersSize; i++) {
            Answer answer = answers.get(i);
            AnswerMark newAnswerMark = AnswerMark.getByAnswerNumberOrThrow(i + 1);
            answer.setAnswerMark(newAnswerMark);

            char answerChar = newAnswerMark.getAnswerChar();
            String newAnswerText = answerChar + answer.getText().substring(1);
            answer.setText(newAnswerText);

            if (answer.isCorrect()) {
                correctAnswerMark = answer.getAnswerMark();
            }
        }

        question.setAnswers(answers);
        question.setCorrectAnswer(correctAnswerMark);
    }
}
