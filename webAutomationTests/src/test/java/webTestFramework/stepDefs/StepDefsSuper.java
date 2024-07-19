package webTestFramework.stepDefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class StepDefsSuper {
    protected static ChromeDriverService service;
    protected static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    protected static WebDriver webDriver;

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        return chromeOptions;
    }
}
