package com.bot.redessociais.client;

import com.bot.redessociais.config.Config;
import com.bot.redessociais.controller.InputController;
import com.bot.redessociais.controller.SocialMediaController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SeleniumClient {

    private InputController inputController;

    private SocialMediaController socialMediaController;

    public static final Logger logger = LogManager.getLogger(SeleniumClient.class);

    public WebDriver driver;

    private Config config;

    public SeleniumClient(WebDriver driver, InputController inputController,SocialMediaController socialMediaController) throws IOException {
        this.driver = driver;
        this.inputController = inputController;
        this.socialMediaController = socialMediaController;
    }

    public void navigate(String url) {
        this.driver.get(url);
    }

    public void close() {
        this.driver.close();
    }

    public WebElement getElement(String xpath) {
        WebElement element = null;
        try {
            element = this.driver.findElement(new By.ByXPath(xpath));
            if (element == null) {
                logger.error(String.format("Element %s not found!", xpath));
                return null;
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return element;
    }

    public void sendKeys(String xpath, String text) {
        try {
            WebElement element = getElement(xpath);
            element.sendKeys(text);
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
    }

    public void sendKeys(WebElement element, String text) {
        try {
            element.sendKeys(text);
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
    }

    public void click(String xpath) {
        try {
            WebElement element = getElement(xpath);
            if (element.isDisplayed() && element.isEnabled())
                element.click();
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
    }

    public void click(WebElement element) {
        try {
            element.click();
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
    }

    public WebElement waitForAndGetElementVisible(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, this.getConfig().getTimeOutElementExists());
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return getElement(xpath);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    public WebElement waitForAnGetElementClickable(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, this.getConfig().getTimeOutElementExists());
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return getElement(xpath);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    public boolean elementExists(String xpath) {
        try {
            return this.driver.findElement(new By.ByXPath(xpath)) != null ? true : false;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public String getText(String xpath) {
        try {
            return waitForAndGetElementVisible(xpath).getText();
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    public String getAttribute(String xpath, String attribute) {
        try {
            return waitForAndGetElementVisible(xpath).getAttribute(attribute);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    public String getTitle() {
        try {
            return this.driver.getTitle();
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    public Iterator<WebElement> getElementList(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, this.getConfig().getTimeOutElementExists());
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            List<WebElement> elements = driver.findElements(By.xpath(xpath));
            return elements.iterator();
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    public Iterator<WebElement> getElementList(WebElement element, String xpath) {
        try {
            List<WebElement> elements = element.findElements(By.xpath(xpath));
            return elements.iterator();
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    public String getCurrentUrl() {
        try {
            return this.driver.getCurrentUrl();
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    public String getLasPathtUri(String url) {
        try {
            URI uri = new URI(url);
            String path = uri.getPath();
            return path.substring(path.lastIndexOf('/') + 1);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    public Config getConfig() throws IOException {
        return new Config().getConfig();
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public InputController getInputController() {
        return inputController;
    }

    public void setInputController(InputController inputController) {
        this.inputController = inputController;
    }

    public SocialMediaController getSocialMediaController() {
        return socialMediaController;
    }

    public void setSocialMediaController(SocialMediaController socialMediaController) {
        this.socialMediaController = socialMediaController;
    }
}
