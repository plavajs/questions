package plavajs.questions.mapper;

import plavajs.questions.model.AnsweredQuestion;
import plavajs.questions.model.QuestionSet;
import plavajs.questions.model.Result;
import plavajs.questions.dto.QuestionResultDto;
import plavajs.questions.dto.ResultDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GeneralMapper {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

    public static ResultDto resultToResultDto(Result result) {
        QuestionSet questionSet = result.getQuestionSet();
        Map<Integer, AnsweredQuestion> questions = questionSet.getSelectedQuestions();
        List<QuestionResultDto> questionResultDtos = questions.entrySet().stream()
                .map(GeneralMapper::questionToQuestionDto)
                .collect(Collectors.toList());

        return new ResultDto(
                dateFormat.format(new Date()),
                questions.size(),
                result.getCorrectAnswersCount(),
                result.getWrongAnswersCount(),
                result.getSuccessfulPercentage(),
                questionResultDtos);
    }

    private static QuestionResultDto questionToQuestionDto(Map.Entry<Integer, AnsweredQuestion> questionEntry) {
        return new QuestionResultDto(
                questionEntry.getValue().getQuestion().getAllQuestionsNumber(),
                questionEntry.getKey() + 1,
                questionEntry.getValue().isAnsweredCorrectly(),
                questionEntry.getValue().getQuestion().getCorrectAnswer(),
                questionEntry.getValue().getChosenAnswer());
    }
}
