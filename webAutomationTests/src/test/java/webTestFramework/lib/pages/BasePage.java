package webTestFramework.lib.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver webDriver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        if (!isCorrectPage()) {
            throw new IllegalStateException("This is not " + this.getClass().getName() + ", " +
                    "current page is: " + webDriver.getCurrentUrl());
        }
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    protected abstract boolean isCorrectPage();
}

