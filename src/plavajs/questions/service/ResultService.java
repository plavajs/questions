package plavajs.questions.service;

import plavajs.questions.mapper.GeneralMapper;
import plavajs.questions.model.Result;
import plavajs.questions.dto.ResultDto;

import java.util.List;

public class ResultService {

    private final FilesManager filesManager;

    public ResultService(FilesManager filesManager) {
        this.filesManager = filesManager;
    }

    public List<ResultDto> loadAllResults() {
        return filesManager.loadAllResultsResources();
    }

    public void saveResult(Result result) {
        ResultDto resultDto = GeneralMapper.resultToResultDto(result);
        filesManager.saveResult(resultDto);
    }
}
