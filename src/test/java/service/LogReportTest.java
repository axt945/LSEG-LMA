package service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.LogReport;
import service.ReportWriter;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class LogReportTest {

    @Test
    public void testGenerateLogReport_nullDuration_writesErrorLog() {
        ReportWriter mockWriter = mock(ReportWriter.class);
        LogReport logReport = new LogReport(mockWriter);

        Map<Long, Long> durations = new HashMap<>();
        durations.put(444L, null);

        logReport.generateLogReport("log.log", durations);

        verify(mockWriter).writeFile("log.log", "Error, job with PID 444 started but never ended!.");
    }

    @Test
    public void testGenerateLogReport_durationOver10_writesError() {
        ReportWriter mockWriter = mock(ReportWriter.class);
        LogReport logReport = new LogReport(mockWriter);

        Map<Long, Long> durations = new HashMap<>();
        durations.put(444L, 11L);

        logReport.generateLogReport("log.log", durations);

        verify(mockWriter).writeFile("log.log", "Error, job with PID 444 took longer than 10 minutes.");
    }

    @Test
    public void testGenerateLogReport_durationOver5_writesWarning() {
        ReportWriter mockWriter = mock(ReportWriter.class);
        LogReport logReport = new LogReport(mockWriter);

        Map<Long, Long> durations = new HashMap<>();
        durations.put(444L, 9L);

        logReport.generateLogReport("log.log", durations);

        verify(mockWriter).writeFile("log.log", "Warning, job with PID 444 took longer than 5 minutes.");
    }

    // if there is not warning or error, then the writeFile method should not be called due to the "continue" for
    // successful behaving logs
    @Test
    public void testGenerateLogReport_durationUnder5_doesNothing() {
        ReportWriter mockWriter = mock(ReportWriter.class);
        LogReport logReport = new LogReport(mockWriter);

        Map<Long, Long> durations = new HashMap<>();
        durations.put(444L, 3L);

        logReport.generateLogReport("log.log", durations);

        verify(mockWriter, never()).writeFile(anyString(), anyString());
    }
}

