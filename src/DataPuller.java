
import Config.Config;
import Data.FantasyData;
import Util.FileUtil;
import Util.JSONUtil;
import Views.Dr11Action;
import Views.TeamView;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import prediction.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

public class DataPuller {


    public static void main(String args[]) throws IOException, ParseException {

//        "deviceName": "emulator-5554",
//                "platformName": "android",
//                "appPackage": "com.app.dream11Pro",
//                "appActivity": "com.app.dream11.dream11.SplashActivity",
//                "noReset": true

        // System.out.println( DateUtil.getDiffBetween(Calendar.getInstance().getTime(), DateUtil.parseDate("Dec 16, 2020")));
        long startTime=System.currentTimeMillis();
        FantasyData fantasyData=null;
        try {
             fantasyData = JSONUtil.deSerialize(FileUtil.getInstance().getStr(), FantasyData.class);
        }catch (Exception e){

        }
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");//"RZ8NA0RFD0R");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        dc.setCapability("appPackage", "com.app.dream11Pro");
        dc.setCapability("appActivity", "com.app.dream11.dream11.SplashActivity");
        dc.setCapability(MobileCapabilityType.NO_RESET, true);//""com.app.dream11.newhome.HomeActivity");

        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        Dr11Action dr11Action = new Dr11Action(driver);

        dr11Action.openLeague(1).openMyTeams();

        TeamView teamView = new TeamView(driver, dr11Action, 1);
        int a = 1;
        //teamView.print();
        if (!Config.isDataPrepared) {
            FileUtil.getInstance().setStr(JSONUtil.serialize(teamView.collectData()));
        }
        else{
            teamView.updateAnnouncementAndSel(fantasyData);
            FileUtil.getInstance().setStr(JSONUtil.serialize(fantasyData));
        }
        FileUtil.getInstance().writer.close();
        FileUtil.getInstance().reader.close();
        if(Config.isLineupAnnounced){
            fantasyData.filterOutUnannouncedPlayers();
            FantasyTeams teams=new FantasyTeams(fantasyData);
            teams.findAllTeams(null);
            teams.showTeams(100);

        }
        //FileUtil.getInstance().writerTeams.close();

        System.out.println("APPLICATION STOPPED AFTER " + (System.currentTimeMillis() - startTime));
    }
}