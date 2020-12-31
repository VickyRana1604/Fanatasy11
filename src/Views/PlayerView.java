package Views;

import Config.Config;
import Data.Player;
import Data.PlayerStats;
import Util.FileUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerView extends Action {
    private String ADD_PLAYER_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.ImageView[2]";
    private String PLAYER_IMAGE_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.ImageView[3]";
    private String PLAYER_NAME_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.TextView[1]";
    private String PLAYER_TEAM_NAME_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.TextView[4]";
    private String PLAYER_ANNOUNCED_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView";
    private String PLAYER_SEL_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.TextView[2]";
    private String PLAYER_CRED_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.TextView[5]";
    private String DATE_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[%s]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]";
    private String PLAYER_PAST_MATCH_POINT_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[%s]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[8]";

    public List<PlayerPastMatchView> playerIndividualMatchViews = new ArrayList<>();


    public String playerName;
    public String playerTeamName;
    public String playerAnnounced = "didn't played last match";
    public String playerCredit;
    public String playerSel;


    public PlayerView(AndroidDriver<AndroidElement> driver, int playerNo) {
        super(driver, playerNo);
        PLAYER_IMAGE_ELEMENT_ID = String.format(PLAYER_IMAGE_ELEMENT_ID, playerNo);
        ADD_PLAYER_ELEMENT_ID = String.format(ADD_PLAYER_ELEMENT_ID, playerNo);
        PLAYER_NAME_ELEMENT_ID = String.format(PLAYER_NAME_ELEMENT_ID, playerNo);
        PLAYER_TEAM_NAME_ELEMENT_ID = String.format(PLAYER_TEAM_NAME_ELEMENT_ID, playerNo);
        PLAYER_ANNOUNCED_ELEMENT_ID = String.format(PLAYER_ANNOUNCED_ELEMENT_ID, playerNo);
        PLAYER_CRED_ELEMENT_ID = String.format(PLAYER_CRED_ELEMENT_ID, playerNo);
        PLAYER_SEL_ELEMENT_ID = String.format(PLAYER_SEL_ELEMENT_ID, playerNo);

        try {
            playerAnnounced = getText("PLAYER_ANNOUNCED_ELEMENT_ID", PLAYER_ANNOUNCED_ELEMENT_ID);
        } catch (Exception e) {
            String newAddPlayer = PLAYER_IMAGE_ELEMENT_ID;
            String newPlayerImage = ADD_PLAYER_ELEMENT_ID;
            String newSel = String.format("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.TextView[3]", index);
            String newTeam = PLAYER_SEL_ELEMENT_ID;
            PLAYER_IMAGE_ELEMENT_ID = newPlayerImage;
            PLAYER_SEL_ELEMENT_ID = newSel;
            PLAYER_TEAM_NAME_ELEMENT_ID = newTeam;
            ADD_PLAYER_ELEMENT_ID = newAddPlayer;
        }
        playerName = getText("PLAYER_NAME_ELEMENT_ID", PLAYER_NAME_ELEMENT_ID);
        playerSel = getText("PLAYER_SEL_ELEMENT_ID", PLAYER_SEL_ELEMENT_ID);
        playerCredit = getText("PLAYER_CRED_ELEMENT_ID", PLAYER_CRED_ELEMENT_ID);
        playerTeamName = getText("PLAYER_TEAM_NAME_ELEMENT_ID", PLAYER_TEAM_NAME_ELEMENT_ID);
        if (!Config.isDataPrepared) {
            click("PLAYER_ELEMENT_ID", PLAYER_IMAGE_ELEMENT_ID);
            waitTillItLoads(String.format(DATE_ELEMENT_ID, 2));
            crawlListFrom(2, DATE_ELEMENT_ID);
            navigateBack();
            waitTillItLoads(PLAYER_IMAGE_ELEMENT_ID);
        }
        checkValidity();
        System.out.print("inserted");
    }


    @Override
    protected void perform(MobileElement el, int index) throws ViewFilterException {
        playerIndividualMatchViews.add(new PlayerPastMatchView(driver, index));
    }

    @Override
    protected void print() throws IOException {
        FileUtil.getInstance().writer.write("\n(" + playerName + "|" + playerTeamName + "|" + playerCredit + "|" + playerSel + playerAnnounced + ")\n");
        for (PlayerPastMatchView matchView : playerIndividualMatchViews) {
            matchView.print();
        }
    }

    @Override
    protected int getCrawlSpan() {
        return 4;
    }

    protected Player collectData() {
        List<PlayerStats> playerStats = new ArrayList<>();
        for (PlayerPastMatchView matchView : playerIndividualMatchViews) {
            playerStats.add(matchView.collectData());
        }
        return new Player(playerName, playerTeamName, playerAnnounced, playerCredit, playerSel, playerStats);
    }

    public void updateAnnouncementAndSel(Player player) {
        if (!player.getPlayerName().equals(playerName))
            throw new RuntimeException("player name " + player.getPlayerName() + "  doesnt matches with " + playerName);
        player.setPlayerAnnounced(playerAnnounced);
        player.playerSel = playerSel;
    }

    private void checkValidity() {
        playerSel.substring(7).replaceAll("%", "");
    }
}
