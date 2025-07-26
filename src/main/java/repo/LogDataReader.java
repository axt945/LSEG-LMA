package repo;

import model.Log;
import model.LogEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class LogDataReader {

    private final Map<Long, LogEntry> logMap;

    public LogDataReader() {
        this.logMap = new HashMap<>();
    }

    public Map<Long, LogEntry> getData(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String[] time_Split = values[0].split(":");
                var time = LocalTime.of(Integer.parseInt(time_Split[0]), Integer.parseInt(time_Split[1]), Integer.parseInt(time_Split[2]));
                var description = values[1];
                var logEntry = values[2].trim();
                long pid = Integer.parseInt(values[3]);

                Log log = new Log(time, description, logEntry, pid);
                if (!logMap.containsKey(pid))  {
                    var start = new LogEntry(log);
                    logMap.put(pid, start);
                } else  {
                    var end = logMap.get(pid).setEnd(log);
                    var start = logMap.get(pid).getStart();
                    logMap.put(pid, new LogEntry(start, end));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logMap;
    }
}
