package model;


import java.time.LocalTime;

public record Log(LocalTime time, String description, String logEntry, long PID) {

}
