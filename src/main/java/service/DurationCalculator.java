package service;

import model.Log;
import model.LogEntry;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class DurationCalculator {

    public static long calculateTimeDifference(LocalTime start, LocalTime end) {
        var duration = Duration.between(start, end);
        return Math.abs(duration.toMinutes());
    }

    public static Map<Long, Long> computeTimeDifference(Map<Long, LogEntry> logMap) {
        Map<Long, Long> logDuration = new HashMap<>();

        Iterator<LogEntry> itr = logMap.values().iterator();
        while (itr.hasNext()) {
            LogEntry element = itr.next();
            var pid = element.getStart().PID();
            var start_time = element.getStart().time();
            var end_time = Optional.ofNullable(element.getEnd())
                    .map(Log::time)
                    .orElse(null);
            if (element.getEnd() != null) {
                logDuration.put(pid, calculateTimeDifference(start_time, end_time));
            } else {
                logDuration.put(pid, null);
            }
        }
        return logDuration;
    }
}
