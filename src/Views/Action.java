package Views;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class Action {

    private final PointOption bottomPoint = PointOption.point(566, 1799);
    private final PointOption slightlyAbovePoint = PointOption.point(562, 1390);
    protected AndroidDriver<AndroidElement> driver;
    public int index;


    protected Action(AndroidDriver<AndroidElement> driver, int index) {
        this.driver = driver;
        this.index = index;
    }

    public void click(String keyName, String elementId) {
        getElement(keyName, elementId, 0).click();
    }

    public String getText(String keyName, String elementId, long spawnTimeMillis) {
        return getElement(keyName, elementId, spawnTimeMillis).getText();
    }

    public void click(String keyName, String elementId, long spawnTimeMillis) {
        getElement(keyName, elementId, spawnTimeMillis).click();
    }

    public String getText(String keyName, String elementId) {
        return getElement(keyName, elementId, 0).getText();
    }

    protected MobileElement getElement(String keyName, String elementId, long waitTimeMillis) {
        long startingTime = System.currentTimeMillis();
        MobileElement el = null;
        while (true) {
            try {
                el = driver.findElementByXPath(elementId);
                break;
            } catch (RuntimeException e) {
                if ((System.currentTimeMillis() - startingTime) >= waitTimeMillis) {
                    System.out.println(keyName + " unable to open");
                    break;
                }
            }
        }
        //System.out.println("TIME: " + (System.currentTimeMillis() - startingTime));
        return el;
    }


    public void navigateBack() {
        while (true) {
            try {
                MobileElement el = driver.findElementByAccessibilityId("Navigate up");
                el.click();
                break;
            } catch (Exception e) {

            }
        }
    }

    protected void waitTillItLoads(String elementId) {
        long startTime = System.currentTimeMillis();
        while (true) {
            try {
                driver.findElementByXPath(elementId);
                return;
            } catch (Exception e) {
                if ((System.currentTimeMillis() - startTime) >= 10000) {
                    break;
                }

            }
        }
    }

    protected void veerticalUpScroll() {
        (new TouchAction(driver))
                .press(bottomPoint)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(0)))
                .moveTo(slightlyAbovePoint)
                .release()
                .perform();
    }

    private void doWorkForListElement(Set<String> stringSet, String keyElementId, int index) {
        MobileElement el = getElement("crawl", String.format(keyElementId, index), 0);
        System.out.print("\n" + el.getText());
        if (!stringSet.contains(el.getText())) {
            try {
                this.perform(el, index);
            } catch (ViewFilterException e) {
                //e.printStackTrace();
            }
            stringSet.add(el.getText());
        }
        System.out.print("\n");
    }

    protected void crawlListFrom(int fromIndex, String keyElementId) {
        int toIndex = this.getCrawlSpan();
        Set<String> stringSet = new HashSet<>();
        int prevSize = -1, index;
        while (prevSize != stringSet.size()) {
            prevSize = stringSet.size();

            for (index = fromIndex; index >= 0; index++) {
                try {
                    doWorkForListElement(stringSet, keyElementId, index);
                } catch (RuntimeException e) {
                    break;
                }
            }

            //if (index == (toIndex+1))
            veerticalUpScroll();
            fromIndex = 2;
        }

//        for (index = 2; index >= 0; index++) {
//            try {
//                doWorkForListElement(stringSet, keyElementId, index);
//            } catch (Exception e) {
//                break;
//            }
//        }
        System.out.println(stringSet.size() + " records INSERTED");
    }

    protected void perform(MobileElement el, int index) throws ViewFilterException {
    }

    protected void print() throws IOException {
    }

    protected int getCrawlSpan() {
        return 0;
    }
}
