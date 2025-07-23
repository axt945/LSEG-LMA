import model.Log;
import model.LogEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<Long, LogEntry> log_Map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Hello KirkLand !!");
//        Log check = new Log(LocalTime.of(4,4,4), "new money", "MUSIC", 444);
//        Log check2 = new Log(LocalTime.of(4,9,4), "new money", "END", 888);
//
//
//        System.out.println(check.toString());

        String filePath = "src/main/resources/logs[7][50][15].log";

        getData(filePath);
//        System.out.println(log_Map.toString());
//        getTimeDifference();

    }

    public static void getData(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int i=0; i<1; i++) {
                    String[] time_Split = values[0].split(":");
                    LocalTime time = LocalTime.of(Integer.parseInt(time_Split[0]), Integer.parseInt(time_Split[1]), Integer.parseInt(time_Split[2]));
                    String description = values[1];
                    String log_Entry = values[2].trim();
                    long P_I_D = Integer.parseInt(values[3]);

                    if (!log_Map.containsKey(P_I_D))  {
                        var start = new LogEntry(new Log(time, description, log_Entry, P_I_D);
                        log_Map.put(P_I_D, start);
                    } else  {
                        var end = log_Map.get(P_I_D).setEnd(new Log(time, description, log_Entry, P_I_D));
                        var start = log_Map.get(P_I_D).getStart();
                        log_Map.put(P_I_D, new LogEntry(start, end));
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long checkTimeDifference(LocalTime start, LocalTime end) {
        Duration duration = Duration.between(start, end);
        return Math.abs(duration.toMinutes());
    }

    public static void getTimeDifference() {
        for (Map.Entry<Long, LogEntry> entry : log_Map.entrySet()) {
//            String key = entry.getKey();
            if (entry.getValue().getEnd().time() != null) {
                long result = checkTimeDifference(entry.getValue().getStart().time(), entry.getValue().getEnd().time());
                System.out.println(result);
            }
        }
    }
}
