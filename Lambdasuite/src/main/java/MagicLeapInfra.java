import com.opencsv.CSVWriter;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class MagicLeapInfra {

    public String username = "prateeks";
    public String accesskey = "IuCSesD83A7LsTFzEKS0Lb6tzvEfBQ38DMkFTEpudatxxxsdjH";
    public RemoteWebDriver driver;
    public String gridURL = "@hub.lambdatest.com/wd/hub"; //"@eu-central-1-hub.lambdatest.com/wd/hub";
    String status;
    String ResolutionValue;
    long quitestoptime;
    long totaltime;
    long totaltimeforlocaltest;
    long Nettotalspeedtest;
    long GeolocationTotal;
    long ResolutionTotal;
    long totaltesttimeDuration;
    String filepath = "D:\\testing report\\results.csv";
    SessionId session;
    List<String[]> data = new ArrayList<>();

    @BeforeTest
    @org.testng.annotations.Parameters(value = {"browser", "version", "platform", "fixedIp", "resolution", "timezone", "geoLocation"})
    public void setUp(String browser, String version, String platform, String fixedIp, String resolution, String timezone, String geoLocation) throws Exception {


        ChromeOptions capabilities = new ChromeOptions();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version", version);
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("build", "Sample < test debug");
        capabilities.setCapability("name", browser + "  " + version + "  " + platform + "  " + fixedIp);
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("video", true);
        capabilities.setCapability("console", true);
        //capabilities.setCapability("w3c", true);
       // capabilities.setCapability("headless", true);
        //capabilities.addArguments("--headless");
        // capabilities.setCapability("geoLocation", geoLocation);


        capabilities.setCapability("resolution", resolution);
         /*capabilities.setCapability("tunnel", true);
        capabilities.setCapability("fixedIP", fixedIp);*/
        //  capabilities.setCapability("selenium_version", "3.141.59");
        File file = new File(filepath);
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);
        String[] header = {"Browser", "version", "resolution", "platform", "SessionID"};
        writer.writeNext(header);


        data.add(new String[]{browser, version, resolution, platform});
        ListIterator<String[]>
                iterator = data.listIterator();
        while (iterator.hasNext()) {

            System.out.println("table values" + data.toString());

            break;
        }
        writer.writeAll(data);
        writer.close();


        try {

            StopWatch driverStart = new StopWatch();
            driverStart.start();
            String url = "http://" + username + ":" + accesskey + gridURL;

            driver = new RemoteWebDriver(new URL(url), capabilities);
            System.out.println(url + "\n" + browser + version + "\n" + platform + "\n" + fixedIp + "\n" + driver);
            session = driver.getSessionId();
            //System.out.println(driver + "Session ID" + "  " + session.toString() + "\n" + browser + version + "\n" + fixedIp);
            driverStart.stop();
            float timeElapsed = driverStart.getTime() / 1000f;
            System.out.println("Driver Setup time in Seconds " + "  " + timeElapsed + "Sec.");


        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception f) {
            System.out.println(f.getMessage());
        }
    }


    @Test(priority = 1)
    @org.testng.annotations.Parameters(value = {"resolution", "browser", "version", "platform"})
    public void DesktopScript(String resolution, String version, String platform, String browser) {
        try {

          /*  driver.get("https://www.savibook.com/comfort-inn-suites/KlamathFalls/dUM/");
            Thread.sleep(30000);
            driver.findElement(By.className("PrimaryButton_button__1oA3K")).click();
            Thread.sleep(30000);
            driver.getTitle();
            driver.getTitle();
            driver.getTitle();
            driver.getTitle();*/
            long startTest;
            long stopTest;


            startTest = System.currentTimeMillis();
            StopWatch teststarted = new StopWatch();
            teststarted.start();
            driver.get("https://lambdatest.github.io/sample-todo-app/");


            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();

            driver.manage().window().maximize();


            driver.get("https://www.youtube.com/watch?v=wpI6XAteXOI");

            String Tiilegot = driver.getTitle();
            System.out.println("Title value" + "  " + Tiilegot);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Actions action = new Actions(driver);
            WebElement doubleclick = driver.findElement(By.id("search"));
            action.doubleClick(doubleclick).perform();

            System.out.println("Double click");
            driver.findElement(By.name("search_query")).sendKeys("lambdaTest");
            WebElement move = driver.findElement(By.id("search-icon-legacy"));
            action.moveToElement(move);
            action.click().build().perform();
            // driver.findElement(By.id("search-icon-legacy")).click();
            System.out.println("scroll down");
            JavascriptExecutor scroll = (JavascriptExecutor) driver;
            scroll.executeScript("window.scrollBy(0,500)");

            JavascriptExecutor scrollup = (JavascriptExecutor) driver;
            scrollup.executeScript("window.scrollBy(0,-500)");
            System.out.println("scroll up");
            Thread.sleep(10000);
            driver.findElement(By.linkText("LambdaTest")).isDisplayed();
            // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            if (driver.findElement(By.linkText("LambdaTest")).isDisplayed()) {
                status ="passed";
                System.out.println("test passed");

            } else {
                status ="failed";
                System.out.println("test Failed");

            }

            stopTest = System.currentTimeMillis();
            totaltime = stopTest - startTest;


            System.out.println("Time taken by the test to execute" + "    " + totaltime / 1000f + "Sec.");

        } catch (Exception y) {
            status = "failed";
            System.out.println(y);
        }

    }

 /*   @Test(priority = 2)
    @org.testng.annotations.Parameters(value = {"browser", "version", "platform"})
    public void tunnelTest(String version, String platform, String browser) {
        long localtesttimestart;
        long localtesttimestop;
        localtesttimestart = System.currentTimeMillis();
        try {
            driver.get("http://localhost:4300");
            driver.findElement(By.xpath("/html/body/div/div/form/div[1]/input")).sendKeys("prateeks");
            driver.findElement(By.xpath("/html/body/div/div/form/div[2]/input")).sendKeys("9582334806");
            //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            if (driver.findElement(By.xpath("/html/body/div/div/form/div[1]/input")).isDisplayed()) {

                status = "passed";
                System.out.println("Tunnel runs successfully");


            } else {

                status = "failed";
                System.out.println("Tunnel failed");

            }
            driver.findElement(By.xpath("/html/body/div/div/form/div[3]/input")).click();

        } catch (Exception j) {
            status = "failed";
            System.out.println(j);
        }

        try {
            driver.get("http://localhost.lambdatest.com:4200/dashboard/");
            //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.findElement(By.xpath("/html/body/script[3]")).isDisplayed();
            status = "passed";

            System.out.println("local.lambdatest.com runs successfully ");
        } catch (Exception f) {
            status = "failed";
            System.out.println(f);
        }
        localtesttimestop = System.currentTimeMillis();
        totaltimeforlocaltest = localtesttimestop - localtesttimestart;

        System.out.println("Time taken by the test to execute in local" + "    " + totaltimeforlocaltest / 1000f);

    }*/

    @org.testng.annotations.Parameters(value = {"resolution", "browser", "version", "platform"})
    @Test(priority = 3)
    public void Resolution(String resolution, String version, String platform, String browser) {
        try {


            long ResolutionStart;
            long ResolutionStop;

            ResolutionStart = System.currentTimeMillis();
            driver.get("http://whatismyscreenresolution.net/");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement browserResolution = driver.findElement(By.xpath("//*[@id=\"resolution\"]"));
            browserResolution.getAttribute("innerText");
            ResolutionValue = browserResolution.getAttribute("innerText");
            ResolutionValue.trim().replaceAll("\\s", "");
            System.out.println("Given resolution in capabilities" + "  " + resolution);

            System.out.println("Resolution value get from the Website " + " " + browserResolution.getAttribute("innerText"));
            if (resolution.equalsIgnoreCase(ResolutionValue)) {


                status = "passed";
                System.out.println("Resolution Matched");
            } else {

                System.out.println("Resolution does not Matched");
                status = "failed";

            }
            ResolutionStop = System.currentTimeMillis();
            ResolutionTotal = ResolutionStop - ResolutionStart;
            System.out.println("Total time took for resolution" + "  " + ResolutionTotal / 1000f + "Sec.");
        } catch (Exception R) {

            System.out.println(R);
        }
    }

    @Test(priority = 4)
    @org.testng.annotations.Parameters(value = {"geoLocation", "browser", "version", "platform"})
    public void Geolocation(String geoLocation, String version, String platform, String browser) {
        try {
            long Geolocationstart;
            long GeolocationStop;
            Geolocationstart = System.currentTimeMillis();
            driver.get("https://www.iplocation.net/find-ip-address");

            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


            WebElement ipLocation = driver.findElement(By.xpath("/html/body/div[1]/section/div/div/div[1]/div[5]/div[3]/div/table/tbody/tr[2]/td"));
            ipLocation.getAttribute("innerText");
            System.out.println("Location of the machine" + "  " + ipLocation.getAttribute("innerText"));
            System.out.println("Given Geolocation in the TestSuite " + "    " + geoLocation);
            status = "passed";
            GeolocationStop = System.currentTimeMillis();
            GeolocationTotal = GeolocationStop - Geolocationstart;
            System.out.println("Total time took for geolocation test" + " " + GeolocationTotal / 1000f + "Sec.");
        } catch (Exception g) {
            System.out.println(g);
            status = "failed";

        }
    }

    @Test(priority = 5)
    @org.testng.annotations.Parameters(value = {"browser", "version", "platform"})
    public void NetSpeed(String version, String platform, String browser) {
        try {
            long NetSpeedteststart;
            long NetSpeedteststop;
            NetSpeedteststart = System.currentTimeMillis();
            driver.get("https://fast.com");

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Thread.sleep(10000);
            WebElement NetSpeed = driver.findElement(By.id("speed-value"));
            NetSpeed.getAttribute("innerText");
            //  System.out.println("machine Net speed" + "    " + NetSpeed.getAttribute("innerText"));

            WebElement NetSpeedUnit = driver.findElement(By.id("speed-units"));
            NetSpeedUnit.getAttribute("innerText");
            System.out.println("machine Net speed" + "    " + NetSpeed.getAttribute("innerText") + NetSpeedUnit.getAttribute("innerText"));
            status = "passed";
            NetSpeedteststop = System.currentTimeMillis();
            Nettotalspeedtest = NetSpeedteststop - NetSpeedteststart;
            System.out.println("Total time took to NetSpeed test" + "  " + Nettotalspeedtest / 1000f + "Sec.");

        } catch (Exception e) {

            status = "failed";
            System.out.println(e.getMessage());
        }
        System.out.println(driver.getCapabilities());

    }

    @AfterTest
    @org.testng.annotations.Parameters(value = {"browser", "version", "platform", "fixedIp"})
    public void tearDown(String version, String platform, String browser, String fixedIp) throws Exception {
        try {
            long quitetimestart;
            long quitetimestop;

            quitetimestart = System.currentTimeMillis();

            if (driver != null) {
                System.out.println(driver + "Session ID" + "  " + session.toString());
                ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
                driver.quit();
                status = "passed";
            } else {
                status = "failed";
            }
            quitestoptime = System.currentTimeMillis();
            quitetimestop = quitestoptime - quitetimestart;
            System.out.println(platform + "  " + browser + "  " + version + "   " + fixedIp + "\n" + "Driver Quite time" + "   " + quitetimestop / 1000f + "Sec.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(platform + "  " + browser + "  " + version + "   " + fixedIp);

        }
    }
}

