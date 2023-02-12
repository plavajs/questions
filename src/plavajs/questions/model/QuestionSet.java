package plavajs.questions.model;

import plavajs.questions.service.FilesManager;
import plavajs.questions.service.QuestionService;
import plavajs.questions.service.ResultService;

import java.awt.image.BufferedImage;
import java.util.Map;

public class QuestionSet {

    private FilesManager filesManager;
    private QuestionService questionService;
    private ResultService resultService;
    private Map<Integer, AnsweredQuestion> selectedQuestions;
    private int selectedQuestionsCount;
    private Integer currentQuestionIndex = 0;
    private Result result;
    private boolean evaluated = false;

    public Map<Integer, AnsweredQuestion> getSelectedQuestions() {
        return selectedQuestions;
    }

    public int getSelectedQuestionsCount() {
        return selectedQuestionsCount;
    }

    public void setSelectedQuestionsCount(int selectedQuestionsCount) {
        this.selectedQuestionsCount = selectedQuestionsCount;
    }

    public AnsweredQuestion getCurrentAnsweredQuestion() {
        return selectedQuestions.get(currentQuestionIndex);
    }

    public BufferedImage getCurrentQuestionImage() {
        return filesManager.loadImage(getCurrentAnsweredQuestion().getQuestion().getAllQuestionsNumber());
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public Result getResult() {
        return result;
    }

    public ResultService getResultService() {
        return resultService;
    }

    public void setupQuestionSet() {
        filesManager = new FilesManager();
        this.questionService = new QuestionService(filesManager);
        this.resultService = new ResultService(filesManager);
        this.selectedQuestions = questionService.pickListOfRandomQuestions(selectedQuestionsCount);
    }

    public void incrementCurrentQuestionIndex() {
        if (currentQuestionIndex + 1 < selectedQuestionsCount){
            currentQuestionIndex++;
        }
    }

    public void decrementCurrentQuestionIndex() {
        if (currentQuestionIndex - 1 >= 0){
            currentQuestionIndex--;
        }
    }

    public void restart() {
        selectedQuestions = questionService.pickListOfRandomQuestions(selectedQuestionsCount);
        currentQuestionIndex = 0;
        evaluated = false;
        result = null;
    }

    public void evaluateQuestionSet() {
        evaluated = true;
        Map<Integer, AnsweredQuestion> questions = selectedQuestions;
        int correctAnswersCount = 0;
        for (Map.Entry<Integer, AnsweredQuestion> questionEntry : questions.entrySet()) {
            if (questionEntry.getValue().isAnsweredCorrectly()) {
                correctAnswersCount++;
            }
        }
        int wrongAnswers = selectedQuestionsCount - correctAnswersCount;
        double successfulPercentage = (double)correctAnswersCount / selectedQuestionsCount * 100;
        result = new Result(this, correctAnswersCount, wrongAnswers, successfulPercentage);
        currentQuestionIndex = 0;
        resultService.saveResult(result);
    }
}
