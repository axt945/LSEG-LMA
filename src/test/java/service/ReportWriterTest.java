package service;

import org.junit.jupiter.api.Test;
import service.ReportWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReportWriterTest {

    @Test
    public void testWriteFile_appendsContentToFile() throws IOException {
        // Create a temporary log report file
        Path tempFile = Files.createTempFile("log_report", ".txt");

        String filePath = tempFile.toString();
        String warningLog = "Warning, job took longer than 5 minutes.";
        String errorLog = "Error, job took longer than 10 minutes.";

        // write the log onto the temporary log report file
        ReportWriter.writeFile(filePath, warningLog);
        ReportWriter.writeFile(filePath, errorLog);

        // carry put assert tests
        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(2, lines.size());
        assertEquals(warningLog, lines.get(0));
        assertEquals(errorLog, lines.get(1));

        Files.deleteIfExists(tempFile);
    }
}
