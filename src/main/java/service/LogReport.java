package service;

import java.util.Map;
import java.util.logging.Logger;

public class LogReport {
    private static final Logger logger = Logger.getLogger(LogReport.class.getName());
    private final ReportWriter writer;

    public LogReport(ReportWriter writer) {
        this.writer = writer;
    }

    public void generateLogReport(String filePath, Map<Long, Long> durations) {
        for (Map.Entry<Long, Long> entry : durations.entrySet()) {
            Long p_i_d = entry.getKey();
            Long duration = entry.getValue();

            String message;
            if (duration == null) {
                message = String.format("Error, job with PID %d started but never ended!.", p_i_d);
                logger.severe(message);
            } else if (duration > 10) {
                message = String.format("Error, job with PID %d took longer than 10 minutes.", p_i_d);
                logger.severe(message);
            } else if (duration > 5) {
                message = String.format("Warning, job with PID %d took longer than 5 minutes.", p_i_d);
                logger.warning(message);
            } else {
                continue;
            }
            writer.writeFile(filePath, message);
        }
    }
}
