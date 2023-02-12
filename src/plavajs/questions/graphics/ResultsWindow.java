package plavajs.questions.graphics;

import plavajs.questions.dto.ResultDto;
import plavajs.questions.mapper.GeneralMapper;
import plavajs.questions.service.ResultService;

import javax.swing.*;
import javax.swing.table.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ResultsWindow extends JDialog {
    private JPanel contentPane;
    private JTable resultsTable;
    private final List<ResultDto> results;

    public ResultsWindow(ResultService resultService) {
        this.results = resultService.loadAllResults();
        setupGraphics();
        setupActions();
    }

    private void setupGraphics() {
        setContentPane(contentPane);
        setTitle("Historie výsledků");
        setModal(true);
        setBounds(600,200, 0, 0);
        setResizable(false);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumn datesColumn = new TableColumn();
        TableColumn correctColumn = new TableColumn();
        TableColumn wrongColumn = new TableColumn();
        TableColumn percentageColumn = new TableColumn();

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn(datesColumn);
        tableModel.addColumn(correctColumn);
        tableModel.addColumn(wrongColumn);
        tableModel.addColumn(percentageColumn);

        int resultsSize = results.size();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy hh:mm:ss");
        Object[] rowData = new Object[4];
        for (int i = resultsSize - 1; i >= 0; i--) {
            ResultDto result = results.get(i);
            Date datum;
            try {
                datum = GeneralMapper.dateFormat.parse(result.getDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            rowData[0] = dateFormat.format(datum);
            rowData[1] = result.getCorrectAnswersCount();
            rowData[2] = result.getWrongAnswersCount();
            rowData[3] = (double) Math.round(result.getSuccessPercentage() * 10) / 10 + "%";
            tableModel.addRow(rowData);
        }

        resultsTable.setModel(tableModel);
        resultsTable.getTableHeader().setReorderingAllowed(false);

        resultsTable.getColumnModel().getColumn(0).setHeaderValue("Datum");
        resultsTable.getColumnModel().getColumn(0).setCellRenderer(renderer);
        resultsTable.getColumnModel().getColumn(0).setResizable(false);
        resultsTable.getColumnModel().getColumn(1).setHeaderValue("Správných odpovědí");
        resultsTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
        resultsTable.getColumnModel().getColumn(1).setWidth(400);
        resultsTable.getColumnModel().getColumn(1).setResizable(false);
        resultsTable.getColumnModel().getColumn(2).setHeaderValue("Chybných odpovědí");
        resultsTable.getColumnModel().getColumn(2).setCellRenderer(renderer);
        resultsTable.getColumnModel().getColumn(2).setWidth(400);
        resultsTable.getColumnModel().getColumn(2).setResizable(false);
        resultsTable.getColumnModel().getColumn(3).setHeaderValue("Úspěšnost");
        resultsTable.getColumnModel().getColumn(3).setCellRenderer(renderer);
        resultsTable.getColumnModel().getColumn(3).setResizable(false);
        resultsTable.getColumnModel().getColumn(3).setWidth(150);

    }

    private void setupActions() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
