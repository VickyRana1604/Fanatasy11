package Views;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Dr11Action extends Action {

    private static final String LEAGUE_ELEMENT_ID= "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[%s]/android.view.ViewGroup";
    private static final String MY_TEAMS_ELEMENT_ID="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.ActionBar.Tab[3]";
    private static final String CREATE_TEAM_ELEMENT_ID= "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.Button";

    public  Dr11Action( AndroidDriver<AndroidElement> driver){
        super(driver,0);
    }

    public Dr11Action openLeague(int leagueNo){
        waitTillItLoads(String.format(LEAGUE_ELEMENT_ID,leagueNo));
        click("LEAGUE_ELEMENT_ID",String.format(LEAGUE_ELEMENT_ID,leagueNo));
        return this;
    }

    public Dr11Action openMyTeams(){
        waitTillItLoads(MY_TEAMS_ELEMENT_ID);
        click("MY_TEAMS_ELEMENT_ID",MY_TEAMS_ELEMENT_ID);
        return this;
    }

    public Dr11Action createTeam(){
        waitTillItLoads(CREATE_TEAM_ELEMENT_ID);
        click("CREATE_TEAM_ELEMENT_ID",CREATE_TEAM_ELEMENT_ID);
        return this;
    }

}
