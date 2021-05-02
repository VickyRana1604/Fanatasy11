package Views;

import Data.FantasyData;
import Util.FileUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamView extends Action {
    private String GO_BACK_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView";

    public List<PlayerTabVew> playerTabVews = new ArrayList<>();

    public TeamView(AndroidDriver<AndroidElement> driver, Dr11Action dr11Action, int teamNo) {
        super(driver, teamNo);
        dr11Action.createTeam();
        playerTabVews.add(new PlayerTabVew(driver, 1));
        playerTabVews.add(new PlayerTabVew(driver, 2));
        playerTabVews.add(new PlayerTabVew(driver, 3));
        playerTabVews.add(new PlayerTabVew(driver, 4));
        click("GO_BACK_ELEMENT_ID", GO_BACK_ELEMENT_ID);
    }

    @Override
    protected void print() throws IOException {
        FileUtil.getInstance().writer.write("\nTEAM NO" + index);
        for (PlayerTabVew playerTabVew : playerTabVews) {
            playerTabVew.print();
        }
    }

    public FantasyData collectData() {
        return new FantasyData(playerTabVews.get(0).collectData(),
                playerTabVews.get(1).collectData(),
                playerTabVews.get(2).collectData(),
                playerTabVews.get(3).collectData());
    }

    public void updateAnnouncementAndSel(FantasyData fantasyData) {
        playerTabVews.get(0).updateAnnouncementAndSel(fantasyData.wkPlayer);
        playerTabVews.get(1).updateAnnouncementAndSel(fantasyData.batPlayer);
        playerTabVews.get(2).updateAnnouncementAndSel(fantasyData.allPlayer);
        playerTabVews.get(3).updateAnnouncementAndSel(fantasyData.bowlPlayer);
        fantasyData.init();
    }
}
