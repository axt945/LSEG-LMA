package service;

import model.Log;
import model.LogEntry;
import org.junit.jupiter.api.Test;
import service.DurationCalculator;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DurationCalculatorTest {

    @Test
    public void testCalculateTimeDifference_positiveDifference() {
        LocalTime start = LocalTime.of(4, 0, 0);
        LocalTime end = LocalTime.of(4, 7, 0);
        long result = DurationCalculator.calculateTimeDifference(start, end);
        assertEquals(7, result);
    }

    @Test
    public void testCalculateTimeDifference_negativeDifference() {
        LocalTime start = LocalTime.of(4, 30, 2);
        LocalTime end = LocalTime.of(4, 0, 0);
        long result = DurationCalculator.calculateTimeDifference(start, end);
        assertEquals(30, result);
    }

    @Test
    public void testCalculateTimeDifference_sameTime() {
        LocalTime time = LocalTime.of(4, 4, 4);
        long result = DurationCalculator.calculateTimeDifference(time, time);
        assertEquals(0, result);
    }

    @Test
    public void testComputeTimeDifference_withValidStartAndEnd() {
        LocalTime start = LocalTime.of(1, 0, 1);
        LocalTime end = LocalTime.of(4, 44, 4);
        long pid = 111L;

        Log startLog = new Log(start, "background job you", "Start", pid);
        Log endLog = new Log(end, "background job you", "End", pid);

        LogEntry logEntry = new LogEntry(startLog, endLog);
        Map<Long, LogEntry> logMap = new HashMap<>();
        logMap.put(pid, logEntry);

        Map<Long, Long> result = DurationCalculator.computeTimeDifference(logMap);

        assertEquals(1, result.size());
        assertEquals(224, result.get(pid));
    }

    @Test
    public void testComputeTimeDifference_missingEnd() {
        LocalTime start = LocalTime.of(4, 4,4);
        var pid = 444L;
        Log startLog = new Log(start, "scheduled task 920", "Start", pid);
        LogEntry logEntry = new LogEntry(startLog);

        Map<Long, LogEntry> logMap = new HashMap<>();
        logMap.put(pid, logEntry);

        Map<Long, Long> result = DurationCalculator.computeTimeDifference(logMap);

        assertEquals(1, result.size());
        assertNull(result.get(pid));
    }
}

