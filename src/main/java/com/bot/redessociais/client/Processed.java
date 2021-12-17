package com.bot.redessociais.client;

import com.bot.redessociais.model.Input;
import com.bot.redessociais.model.User;
import org.openqa.selenium.WebDriver;

public class Processed {

    private Input input;
    private User user;
    private WebDriver driver;

    public Processed(Input input,User user, WebDriver driver) {
        this.input = input;
        this.user = user;
        this.driver = driver;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
