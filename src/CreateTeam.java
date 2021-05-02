import Config.Config;
import Data.FantasyData;
import Util.FileUtil;
import Util.JSONUtil;
import prediction.FantasyTeams;

import java.io.IOException;

public class CreateTeam {
    public void run(FantasyData fantasyData) throws IOException {
        if (fantasyData.hasAnnouncedData || Config.startFindingTeam) {
            fantasyData.init();
            fantasyData.wkPlayer = fantasyData.filterOutUnannouncedPlayers(fantasyData.wkPlayer);
            fantasyData.batPlayer = fantasyData.filterOutUnannouncedPlayers(fantasyData.batPlayer);
            fantasyData.allPlayer = fantasyData.filterOutUnannouncedPlayers(fantasyData.allPlayer);
            fantasyData.bowlPlayer = fantasyData.filterOutUnannouncedPlayers(fantasyData.bowlPlayer);
            FantasyTeams teams = new FantasyTeams(fantasyData);
            teams.findAllTeams(null);
            FileUtil.getInstance().printTeams("TEAM STARTS FROM HERE\n");
            teams.showTeams(10000);
        }
        FileUtil.getInstance().close();
    }

    public static void main(String[] args) throws IOException {
        FantasyData fantasyData = null;

        //Config.TEAM1_NAME="MI";
        //Config.TEAM2_NAME="RR";
        long startTime = System.currentTimeMillis();
        try {
            fantasyData = JSONUtil.deSerialize(FileUtil.getInstance().getStr(), FantasyData.class);
        } catch (Exception e) {
            int a = 1;
        }

        new CreateTeam().run(fantasyData);

        System.out.println("APPLICATION STOPPED AFTER " + (System.currentTimeMillis() - startTime));
    }
}
