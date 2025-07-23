package model;

public class LogEntry {

    private Log start;
    private Log end;

    public LogEntry(Log start) {
        this.start = start;
        this.end = null;
    }

    public LogEntry(Log start, Log end) {
        this.start = start;
        this.end = end;
    }

    public Log getStart() {
        return start;
    }

    public void setStart(Log start) {
        this.start = start;
    }

    public Log getEnd() {
        return end;
    }

    public Log setEnd(Log end) {
        this.end = end;
        return end;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
