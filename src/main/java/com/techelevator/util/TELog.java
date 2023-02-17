package com.techelevator.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class TELog {
    private static final String logFilePath = "capstone/logs/log.txt";
//    private static final String logFilePath = "/Users/chexpeare/MeritAmerica/PairProgrammingBackup/pair-programming-4/logs/log.txt";

    public static void log(String message) throws IOException {
        File outputFile = new File(logFilePath);
        String canonical = outputFile.getCanonicalPath();

        try (FileOutputStream stream = new FileOutputStream(canonical, true);
             PrintWriter writer = new PrintWriter(stream)){

            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
            writer.println(">" + timestamp.format(formatter) + " : " + message);

        } catch (Exception e) {
            throw new TELogException(e.getMessage() + "occurred while trying to write " + message + " to the log.");
        }
    }
}
