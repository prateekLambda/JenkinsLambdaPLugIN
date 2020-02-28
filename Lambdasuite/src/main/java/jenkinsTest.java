import org.openqa.selenium.remote.RemoteWebDriver;

public class jenkinsTest {
    public static RemoteWebDriver driver;
    public static void main(String args[]){
        try {
            driver.get("https://www.google.com");
            Thread.sleep(20000);
        }
        catch (Exception e){

            System.out.println(e);
            driver.quit();
        }
    }
}
