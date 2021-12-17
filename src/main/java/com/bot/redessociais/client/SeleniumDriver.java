package com.bot.redessociais.client;

import com.bot.redessociais.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.List;

public class SeleniumDriver {

    private long id;
    private WebDriver driver;
    private boolean inUse = false;

    private Config config;

    public SeleniumDriver(long id, WebDriver driver) throws IOException {
        this.id = id;
        if (driver != null) {
            this.driver = driver;
            return;
        }
        ChromeOptions options = new ChromeOptions();
        this.config = new Config().getConfig();
        System.setProperty("webdriver.chrome.driver", this.config.getChromedriver_path());
        List<String> localOptions = this.config.getChromeOptions();
        if (localOptions != null) {
            for (String option : localOptions) {
                options.addArguments(option);
            }
        }
        this.driver = new ChromeDriver(options);
        this.inUse = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
