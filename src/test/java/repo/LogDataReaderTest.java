package repo;

import model.LogEntry;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LogDataReaderTest {

    @Test
    public void testGetData() throws IOException {
        // Create a temp file with two log entries (START and END) for a single task
        Path tempFile = Files.createTempFile("log_data", ".log");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            writer.write("04:44:00,BackupJob,START,444\n");
            writer.write("04:48:00,BackupJob,END,444\n");
        }

        // Pass the temp file to the log data reader
        LogDataReader reader = new LogDataReader();
        Map<Long, LogEntry> logMap = reader.getData(tempFile.toString());

        // Test to see if assert conditions are met
        assertEquals(1, logMap.size());

        LogEntry entry = logMap.get(444L);
        assertNotNull(entry);
        assertNotNull(entry.getStart());
        assertNotNull(entry.getEnd());

        assertEquals(LocalTime.of(4, 44, 0), entry.getStart().time());
        assertEquals(LocalTime.of(4, 48, 0), entry.getEnd().time());
        assertEquals("START", entry.getStart().logEntry());
        assertEquals("END", entry.getEnd().logEntry());

        Files.deleteIfExists(tempFile);
    }
}
