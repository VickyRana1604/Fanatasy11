package Views;

import Data.Player;
import Util.FileUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerTabVew extends Action {
    private String PLAYER_NAME_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.TextView[1]";
    private String PLAYER_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.ImageView[3]";
    private String TAB_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.ActionBar.Tab[%s]";
    private String PLAYER_ANNOUNCED_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[%s]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView";
    public List<PlayerView> playerViews = new ArrayList<>();

    public PlayerTabVew(AndroidDriver<AndroidElement> driver, int tabNo) {
        super(driver, tabNo);
        if(tabNo!=4)return;
        TAB_ELEMENT_ID = String.format(TAB_ELEMENT_ID, tabNo);
        waitTillItLoads(TAB_ELEMENT_ID);
        click("TAB_ELEMENT_ID", TAB_ELEMENT_ID);
        waitTillItLoads(String.format(PLAYER_NAME_ELEMENT_ID, 1));
        crawlListFrom(1, PLAYER_NAME_ELEMENT_ID);

        //perform(null,1);
    }

    @Override
    protected void perform(MobileElement el, int index) {
        // int a=1;
        //if (getText("PLAYER_ANNOUNCED_ELEMENT_ID",String.format(PLAYER_ANNOUNCED_ELEMENT_ID, index)).equals("⬤ Announced"))
        playerViews.add(new PlayerView(driver, index));
    }

    @Override
    protected void print() throws IOException {

        FileUtil.getInstance().writer.write("\nTAB " + index);
        for (PlayerView playerView : playerViews) {
            playerView.print();
        }
    }

    @Override
    protected int getCrawlSpan() {
        return 5;
    }

    protected List<Player> collectData() {
        List<Player> players = new ArrayList<>();
        for (PlayerView playerView : playerViews) {
            players.add(playerView.collectData());
        }
        return players;
    }

    public void updateAnnouncementAndSel(List<Player> players) {
        if (players.size() != playerViews.size()) throw new RuntimeException("player list size doesnt matches");
        for (int i = 0; i < players.size(); i++) {
            playerViews.get(i).updateAnnouncementAndSel(players.get(i));
        }
    }
}
