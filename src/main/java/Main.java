import model.Log;
import model.LogEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Main {

    private static Map<Long, LogEntry> log_Map = new HashMap<>();
    private static final String START = "START";
    private static final String END = "END";

    public static void main(String[] args) {
        System.out.println("Hello KirkLand !!");
//        Log check = new Log(LocalTime.of(4,4,4), "new money", "MUSIC", 444);
//        Log check2 = new Log(LocalTime.of(4,9,4), "new money", "END", 888);
//
//
//        System.out.println(check.toString());
        var s_ = LocalTime.of(2,2,2);
        var s = LocalTime.of(4,4,4);
        var _s = LocalTime.of(3,3,3);


        String filePath = "src/main/resources/logs[7][50][15].log";

        getData(filePath);
        System.out.println(log_Map.values());
//        System.out.println(log_Map.toString());
//        getTimeDifference(s, _s);
        System.out.println("to fly");
        System.out.println(getTimeDifference());
//        loop();

    }

    // data retrieval class
    public static void getData(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                    String[] time_Split = values[0].split(":");
                    var time = LocalTime.of(Integer.parseInt(time_Split[0]), Integer.parseInt(time_Split[1]), Integer.parseInt(time_Split[2]));
                    var description = values[1];
                    var log_Entry = values[2].trim();
                    long P_I_D = Integer.parseInt(values[3]);

                    if (!log_Map.containsKey(P_I_D))  {
                        var start = new LogEntry(new Log(time, description, log_Entry, P_I_D));
                        log_Map.put(P_I_D, start);
                    } else  {
                        var end = log_Map.get(P_I_D).setEnd(new Log(time, description, log_Entry, P_I_D));
                        var start = log_Map.get(P_I_D).getStart();
                        log_Map.put(P_I_D, new LogEntry(start, end));
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // time calculation class
    public static long checkTimeDifference(LocalTime start, LocalTime end) {
        var duration = Duration.between(start, end);
        return Math.abs(duration.toMinutes());
    }

//    public static long getTimeDifference(LocalTime start, LocalTime end) {
////            var end_log = log_Map.get(START).getEnd();
////            var start_log = log_Map.get(END).getEnd();
////            if (end_log != null) {
////                long result = checkTimeDifference(start_log.time(), end_log.time());
////        }
//        return checkTimeDifference(start, end);
//    }

    // get time difference
    public static  Map<Long, Long> getTimeDifference() {
        Collection<LogEntry> logEntryValues = log_Map.values();
        Map<Long, Long> logDuration = new HashMap<>();

        Iterator<LogEntry> itr = logEntryValues.iterator();
        while (itr.hasNext()) {
            LogEntry element = itr.next();
            var id = element.getStart().PID();
            var start_time = element.getStart().time();
            var end_time = Optional.of(element.getEnd().time()).orElse(null);
            if (element.getEnd() != null) {
                logDuration.put(id, checkTimeDifference(start_time, end_time));
            } else {
                logDuration.put(id, null);
            }
        }

        return logDuration;
    }

    // error catch class
//    public static void loop() {
//        for (Map.Entry<Long, LogEntry> entry : log_Map.entrySet()) {
//            var start = entry.getValue().getStart();
//            var end = entry.getValue().getEnd();
//            getTimeDifference(start, end);
//        }
//    }



}
