package Views;

import Data.PlayerStats;
import Util.FileUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;

public class PlayerPastMatchView extends Action {
    private String PLAYER_SEL_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[%s]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[7]";
    private String PLAYER_POINT_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[%s]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[8]";
    private String PLAYER_CREDIT_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[%s]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[9]";
    private String VS_TEAM_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[%s]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[2]";
    private String DATE_ELEMENT_ID = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[%s]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]";

    public String sel;
    public String point;
    public String credit;
    public String vsTeam;
    public String date;

    public PlayerPastMatchView(AndroidDriver<AndroidElement> driver, int matchNo) throws ViewFilterException {
        super(driver, matchNo);
        PLAYER_CREDIT_ELEMENT_ID = String.format(PLAYER_CREDIT_ELEMENT_ID, matchNo);
        PLAYER_POINT_ELEMENT_ID = String.format(PLAYER_POINT_ELEMENT_ID, matchNo);
        PLAYER_SEL_ELEMENT_ID = String.format(PLAYER_SEL_ELEMENT_ID, matchNo);
        VS_TEAM_ELEMENT_ID = String.format(VS_TEAM_ELEMENT_ID, matchNo);
        DATE_ELEMENT_ID = String.format(DATE_ELEMENT_ID, matchNo);
        sel = getText("PLAYER_SEL_ELEMENT_ID", PLAYER_SEL_ELEMENT_ID);
        point = getText("PLAYER_POINT_ELEMENT_ID", PLAYER_POINT_ELEMENT_ID);
        if (point.equals("DNP")) {
            throw new ViewFilterException("Player didn't  played this match");
        }
        credit = getText("PLAYER_CREDIT_ELEMENT_ID", PLAYER_CREDIT_ELEMENT_ID);
        vsTeam = getText("VS_TEAM_ELEMENT_ID", VS_TEAM_ELEMENT_ID);
        date = getText("DATE_ELEMENT_ID", DATE_ELEMENT_ID);
        checkValidity();
        System.out.print("inserted");
    }

    @Override
    protected void print() throws IOException {
        FileUtil.getInstance().writer.write("(" + vsTeam + "|" + date + "|" + sel + "|" + point + "|" + credit + ") ");
    }

    protected PlayerStats collectData() {
        return new PlayerStats(vsTeam, date, sel, point, credit);
    }

    private void checkValidity() {
        if (sel.charAt(sel.length() - 1) != '%') throw new RuntimeException("Invalid sel");
        Integer.parseInt(point);
        if (credit.charAt(1) != '.') throw new RuntimeException("Invalid credit");
    }
}
