package Views;

import Config.Config;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.Collections;

public class Dr11Action extends Action {
    ///hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[5]
    private static final String LEAGUE_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%d]";
    private static final String MY_TEAMS_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.LinearLayout[3]";
    private static final String CREATE_TEAM_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.Button";
    private static final String TEAM_ELEMENT_ID = LEAGUE_ELEMENT_ID + "/android.widget.TextView[%d]";
    private static final String LINEUPOUT = LEAGUE_ELEMENT_ID + "/android.widget.TextView[2]";

    public Dr11Action(AndroidDriver<AndroidElement> driver) {
        super(driver, 0);
    }

    public Dr11Action openLeague(int leagueNo) {
        waitTillItLoads(String.format(LEAGUE_ELEMENT_ID, leagueNo));
        String lineup = getText("LINEUPOUT", String.format(LINEUPOUT, Config.leagueNo), 100);

        if(!lineup.equals("Lineups Out")) {
            Config.isLineupAnnounced = false;
            Config.TEAM1_NAME = getText("TEAM1_ELEMENT_ID", String.format(TEAM_ELEMENT_ID, Config.leagueNo, 5));
            Config.TEAM2_NAME = getText("TEAM2_ELEMENT_ID", String.format(TEAM_ELEMENT_ID, Config.leagueNo, 6));

        }else {
            Config.TEAM1_NAME = getText("TEAM1_ELEMENT_ID", String.format(TEAM_ELEMENT_ID, Config.leagueNo, 6));
            Config.TEAM2_NAME = getText("TEAM2_ELEMENT_ID", String.format(TEAM_ELEMENT_ID, Config.leagueNo, 7));
            Config.isLineupAnnounced = true;
        }
        click("LEAGUE_ELEMENT_ID", String.format(LEAGUE_ELEMENT_ID, leagueNo));
        return this;
    }

    public Dr11Action openMyTeams() {
        waitTillItLoads(MY_TEAMS_ELEMENT_ID);
        click("MY_TEAMS_ELEMENT_ID", MY_TEAMS_ELEMENT_ID);
        return this;
    }

    public Dr11Action createTeam() {
        waitTillItLoads(CREATE_TEAM_ELEMENT_ID);
        click("CREATE_TEAM_ELEMENT_ID", CREATE_TEAM_ELEMENT_ID);
        return this;
    }
}
