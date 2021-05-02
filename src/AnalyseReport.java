import Config.Config;
import Data.FantasyData;
import Data.Player;
import Util.FileUtil;
import Util.JSONUtil;
import prediction.FantasyTeams;

import java.io.IOException;
import java.util.Scanner;

public class AnalyseReport {

    private static void getResult(FantasyData fantasyData) {
        Scanner scanner = new Scanner(System.in);
        for (Player p : fantasyData.wkPlayer) {
            System.out.println(p.getPlayerName());
            p.result = Double.parseDouble(scanner.nextLine());
        }
        for (Player p : fantasyData.batPlayer) {
            System.out.println(p.getPlayerName());
            p.result = Double.parseDouble(scanner.nextLine());
        }
        for (Player p : fantasyData.allPlayer) {
            System.out.println(p.getPlayerName());
            p.result = Double.parseDouble(scanner.nextLine());
        }
        for (Player p : fantasyData.bowlPlayer) {
            System.out.println(p.getPlayerName());
            p.result = Double.parseDouble(scanner.nextLine());
        }
    }

    public static void main(String[] args) throws Exception {
        FantasyData fantasyData = null;

        long startTime = System.currentTimeMillis();
        fantasyData = JSONUtil.deSerialize(FileUtil.getInstance().getStr(), FantasyData.class);

        if (fantasyData.hasAnnouncedData) {
            getResult(fantasyData);
            fantasyData.init();
            fantasyData.wkPlayer = fantasyData.filterOutUnannouncedPlayers(fantasyData.wkPlayer);
            fantasyData.batPlayer = fantasyData.filterOutUnannouncedPlayers(fantasyData.batPlayer);
            fantasyData.allPlayer = fantasyData.filterOutUnannouncedPlayers(fantasyData.allPlayer);
            fantasyData.bowlPlayer = fantasyData.filterOutUnannouncedPlayers(fantasyData.bowlPlayer);
            FantasyTeams teams = new FantasyTeams(fantasyData);
            teams.findAllTeams(null);
            teams.showGraph(10000);
        }else throw new Exception("Doesnt have announced Data");

        FileUtil.getInstance().close();

        System.out.println("APPLICATION STOPPED AFTER " + (System.currentTimeMillis() - startTime));
    }
}
