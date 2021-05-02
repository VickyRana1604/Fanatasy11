package Util;

import Config.Config;

import java.io.*;
import java.time.LocalDate;

public class FileUtil {
    private static FileUtil util;
    private static final String basePath = "D:/teams/";

    private static final String filePathTeams = basePath + "teams.txt";

    public static FileUtil getInstance() {
        if (util == null) {
            try {
                util = new FileUtil();
            } catch (Exception e) {
            }
        }
        return util;
    }

    public BufferedWriter writer;
    public BufferedWriter writerTeams;
    public BufferedWriter writerGraph;
    public BufferedReader reader;

    public FileUtil() throws IOException {
    }

    public String getStr() {
        try {
            String filePath = basePath + Config.TEAM1_NAME + "-VS-" + Config.TEAM2_NAME + LocalDate.now()+".json";

            if (reader == null)
                reader = new BufferedReader(new FileReader(filePath));

            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setStr(String s) {
        try {
            String filePath = basePath + Config.TEAM1_NAME + "-VS-" + Config.TEAM2_NAME + LocalDate.now()+".json";
            if (writer == null)
                writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(s);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTeams(String s) {
        try {
            String filePath = basePath + Config.TEAM1_NAME + "-VS-" + Config.TEAM2_NAME + LocalDate.now()+"DR11Teams.txt";

            if (writerTeams == null)
                writerTeams = new BufferedWriter(new FileWriter(filePath));
            writerTeams.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void plotGraph(String s) {
        try {
            String filePath = basePath + Config.TEAM1_NAME + "-VS-" + Config.TEAM2_NAME + LocalDate.now()+"REPORT.txt";
            if (writerGraph == null)
                writerGraph = new BufferedWriter(new FileWriter(filePath));
            writerGraph.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (reader != null) reader.close();
            if (writerTeams != null) writerTeams.close();
            if (writer != null) writer.close();
            if (writerGraph != null) writerGraph.close();
        } catch (Exception e) {
        }
    }
}
