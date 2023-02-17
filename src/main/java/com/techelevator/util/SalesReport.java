package com.techelevator.util;

import com.techelevator.view.Menu;
import com.techelevator.Purchase;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class SalesReport {
    static final String logFilePath = "capstone/logs/SalesReport.txt";
    static final String salesReportFile = "capstone/logs/" + fileName() + ".txt";
    static LocalDateTime nowTimeStamp = LocalDateTime.now();

    /** CONSTRUCTOR */
    public SalesReport(Menu menu) {

    }

    public static void log(String logMessage) throws IOException {
        File outputFile = new File(logFilePath);
        String canonical = outputFile.getCanonicalPath();

        try (FileOutputStream stream = new FileOutputStream(canonical, true);
             PrintWriter writer = new PrintWriter(stream)){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
            writer.println(nowTimeStamp.format(formatter) + " : " + logMessage);

        } catch (Exception e) {
            throw new SalesReportException(e.getMessage() + "occurred while trying to write " + logMessage + " to the log.");
        }
    }

    public static void appendToSalesReport(String salesReportMessage) throws IOException {
        nowTimeStamp = LocalDateTime.now();
        File outputFile = new File(salesReportFile);
        String canonical = outputFile.getCanonicalPath();

        try (FileOutputStream stream = new FileOutputStream(canonical, true);
             PrintWriter writer = new PrintWriter(stream)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
//            writer.println(salesReportMessage + "\n" + nowTimeStamp.format(formatter));
            writer.println(salesReportMessage);

        } catch (Exception e) {
            throw new SalesReportException(e.getMessage() + "occurred while trying to create " + salesReportFile + ".");
        }
//        SalesReport.appendToSalesReport("We hope you enjoyed using the Vendo-Matic 800"
//                + "\nThank you for shopping at The Umbrella Academy!");
    }


    public static void displaySalesReport() throws IOException {
        System.out.println("\n=========================\n"
                + "  \33[34;1mTHE UMBRELLA ACADEMY\33[0m\n"
                + "      SALES REPORT\n"
                + "-------------------------");

        BufferedReader reader = null;
        try {
            File inputFile = new File(salesReportFile);
            String canonical = inputFile.getCanonicalPath();
            FileReader readerFile = new FileReader(canonical);
            reader = new BufferedReader(readerFile);
            String currentLine;
//        System.out.println("Building SalesReport: " + currentLine);
            while( (currentLine = reader.readLine() ) != null) {
                System.out.println(currentLine);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("\n=========================");
            System.out.println("     \33[34;1mVENDO-MATIC 800\33[0m");
            System.out.println("-------------------------");
            System.out.println("\33[31mSales Report not created.\33[0m");
            System.out.println("\33[32;1mNo items were purchased.\33[0m");
            System.out.println("-------------------------");
        } finally {
            System.out.println("-------------------------");
            System.out.println(" Thank you for shopping!\n" +
                               "     \33[34;1mVENDO-MATIC 800\33[0m");
            System.out.println("=========================");
        }

        }

    private static String fileName() {
        nowTimeStamp = LocalDateTime.now();

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MMddyyHHmmss");
        String unformattedDateTime = dateTimeFormat.format(nowTimeStamp);

        LocalDateTime localDateTime = LocalDateTime.parse(unformattedDateTime, dateTimeFormat) ;
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern( "MMddyy_HHmmss" ) ;
        String fileTimeHeader = localDateTime.format(formatPattern);

        return fileTimeHeader + "_SalesReport";
    }

}
