import model.LogEntry;
import repo.LogDataReader;
import service.DurationCalculator;
import service.LogReport;
import service.ReportWriter;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String inFilePath = "src/main/resources/logs[7][50][15].log";
        String outFilePath = "src/main/resources/logsReport.log";

        LogDataReader readData = new LogDataReader();
        Map<Long, LogEntry> logMap = readData.getData(inFilePath);

        Map<Long, Long>  durations = DurationCalculator.computeTimeDifference(logMap);
        ReportWriter writer = new ReportWriter();
        LogReport reporter = new LogReport(writer);

        reporter.generateLogReport(outFilePath, durations);

    }
}
