package webTestFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private By startButton = By.id("start-button");

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected boolean isCorrectPage() {
        return webDriver.getTitle().equals("Home Page");
    }

    public SummaryToolPage clickStartButton() {
        webDriver.findElement(startButton).click();
        return new SummaryToolPage(webDriver);
    }
}
