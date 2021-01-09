package Util;

import Config.Config;

import java.io.*;

public class FileUtil {
    private static FileUtil util;
    private static final String filePath = "D:/teams/" + ((Config.TEAM1_NAME == null) ? "data2" : (Config.TEAM1_NAME + "vs" + Config.TEAM2_NAME)) + ".json";

    private static final String filePathTeams ="D:/teams/teams.txt";

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
    public BufferedReader reader;

    public FileUtil() throws IOException {
    }

    public String getStr() {
        try {
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
            if (writer == null)
                writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTeams(String s) {
        try {
            if (writerTeams == null)
                writerTeams = new BufferedWriter(new FileWriter(filePathTeams));
            writerTeams.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
