package webTestFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SummaryToolPage extends BasePage{
    private By uploadIcon = By.id("upload-icon");
    private By summariseButton = By.id("summarise-button");
    private By summaryOutput = By.id("summary-output");

    public SummaryToolPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected boolean isCorrectPage() {
        return webDriver.getTitle().equals("Summary Tool");
    }

    public void clickUploadIcon() {
        WebElement icon = webDriver.findElement(uploadIcon);
        icon.click();
    }

    public void clickSummariseButton() {
        WebElement button = webDriver.findElement(summariseButton);
        button.click();
    }

    public String getSummary() {
        WebElement summaryElement = webDriver.findElement(summaryOutput);
        return summaryElement.getText();
    }
}
