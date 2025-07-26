package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportWriter {

    public static void writeFile(String filePath, String description) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(description);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
