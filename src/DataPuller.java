
import Config.Config;
import Data.FantasyData;
import Data.Player;
import Util.FileUtil;
import Util.JSONUtil;
import Views.Dr11Action;
import Views.TeamView;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;
import prediction.*;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Scanner;

public class DataPuller {

    public static void main(String args[]) throws IOException, ParseException {
        Config.TEAM1_NAME = "MI";
        Config.TEAM2_NAME = "CSK";

        run();


    }

    public static AndroidDriver<AndroidElement> startServer() throws IOException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");//"RZ8NA0RFD0R");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        dc.setCapability("appPackage", "com.app.dream11Pro");
        dc.setCapability("appActivity", "com.app.dream11.dream11.SplashActivity");
        dc.setCapability(MobileCapabilityType.NO_RESET, true);//""com.app.dream11.newhome.HomeActivity");


//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder.withIPAddress("127.0.0.1");
//        builder.usingPort(4723);
//        builder.withCapabilities(dc);
//        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
//
//        //Start the server with the builder
//        DriverService service = AppiumDriverLocalService.buildService(builder);
//        service.start();
        return new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
    }
    public static void run() throws IOException, ParseException {

//        "deviceName": "emulator-5554",
//                "platformName": "android",
//                "appPackage": "com.app.dream11Pro",
//                "appActivity": "com.app.dream11.dream11.SplashActivity",
//                "noReset": true

        long startTime = System.currentTimeMillis();

        AndroidDriver<AndroidElement> driver = startServer();
        Dr11Action dr11Action = new Dr11Action(driver);

        dr11Action.openLeague(Config.leagueNo);

        FantasyData fantasyData = null;

        try {
            fantasyData = JSONUtil.deSerialize(FileUtil.getInstance().getStr(), FantasyData.class);
        } catch (Exception e) {
            int a = 1;
        }
        if (fantasyData == null||!fantasyData.hasCompleteData) {
            dr11Action.openMyTeams();
            TeamView teamView = new TeamView(driver, dr11Action, 1);
            fantasyData = teamView.collectData();
        } else if (Config.isLineupAnnounced && fantasyData.hasAnnouncedData == false) {
            Config.isDataPrepared = true;
            dr11Action.openMyTeams();
            TeamView teamView = new TeamView(driver, dr11Action, 1);
            teamView.updateAnnouncementAndSel(fantasyData);
        }

        fantasyData.hasCompleteData = true;
        fantasyData.hasAnnouncedData = Config.isLineupAnnounced;
        int a = 1;
        //getResult(fantasyData);
        FileUtil.getInstance().setStr(JSONUtil.serialize(fantasyData));
        new CreateTeam().run(fantasyData);

        FileUtil.getInstance().close();
        System.out.println("APPLICATION STOPPED AFTER " + (System.currentTimeMillis() - startTime));
    }
}