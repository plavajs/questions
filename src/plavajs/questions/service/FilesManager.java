package plavajs.questions.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import plavajs.questions.App;
import plavajs.questions.model.Question;
import plavajs.questions.dto.ResultDto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class FilesManager {
    private final String ALL_QUESTIONS_PATH = "/all-questions.json";
    private final String ALL_IMAGES_PATH = "/images/";
    private String RESULTS_PATH;

    public FilesManager() {
        createResultsPath();
    }

    private InputStream getResourceAsStream(String path) {
        return App.class.getResourceAsStream(path);
    }

    public List<Question> loadAllQuestionsResource() {
        List<Question> questions = new ArrayList<>();

        try (InputStream inputStream = getResourceAsStream(ALL_QUESTIONS_PATH)) {
            ObjectMapper mapper = new ObjectMapper();
            questions = mapper.readValue(inputStream, new TypeReference<List<Question>>(){});
        } catch (IOException e) {
            System.err.println("Unable to read questions resource!!!");
            e.printStackTrace();
        }

        return questions;
    }

    public BufferedImage loadImage(int imageNumber) {
        try (InputStream inputStream = getResourceAsStream(String.format("%s%d.png",ALL_IMAGES_PATH, imageNumber))) {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            System.err.println("Unable to read image resource!!!");
            e.printStackTrace();
            return null;
        }
    }

    private void createResultsPath() {
        try {
            String appPath = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            String fileSeparator = String.format("\\%s", File.separator);
            String[] appPathByFolder = appPath.split(fileSeparator);
            StringBuilder resultsPath = new StringBuilder();
            int appPathLength = appPathByFolder.length;
            for (int i = 0; i < appPathLength - 1; i++) {
                resultsPath.append(appPathByFolder[i]).append(File.separator);
            }

            resultsPath.append("results").append(File.separator);
            RESULTS_PATH = resultsPath.toString();

        } catch (URISyntaxException e) {
            RESULTS_PATH = System.getProperty("user.home") + "/questions/results/";
            System.err.println("Unable to create results path!!!");
            e.printStackTrace();
        }

    }

    public void saveResult(ResultDto resultDto) {
        File resultFolder = new File(RESULTS_PATH);
        resultFolder.mkdirs();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File resultsFile = new File(RESULTS_PATH + "questions-result_" + resultDto.getDate() + ".json");
            objectMapper.writeValue(resultsFile, resultDto);
        } catch (IOException e) {
            System.err.println("Unable to save result!!!");
            e.printStackTrace();
        }
    }

    public List<ResultDto> loadAllResultsResources() {
        List<ResultDto> results = new ArrayList<>();

        File resultFolder = new File(RESULTS_PATH);
        resultFolder.mkdirs();
        List<String> resultFileNames = Arrays.stream(Objects.requireNonNull(resultFolder.listFiles()))
                .map(File::getName)
                .collect(Collectors.toList());

        if (!resultFileNames.isEmpty()) {
            results = resultFileNames.stream()
                    .map(this::loadResultResource)
                    .collect(Collectors.toList());
        }

        return results;
    }

    private ResultDto loadResultResource(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(RESULTS_PATH + fileName), ResultDto.class);
        } catch (IOException e) {
            System.err.printf("Unable to read result %s!!!%n", fileName);
            e.printStackTrace();
        }
        return null;
    }
}
