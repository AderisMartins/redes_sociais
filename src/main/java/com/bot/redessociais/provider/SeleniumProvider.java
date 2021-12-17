package com.bot.redessociais.provider;

import com.bot.redessociais.client.Processed;
import com.bot.redessociais.client.action.facebook.FacebookAction;
import com.bot.redessociais.client.SeleniumClient;
import com.bot.redessociais.controller.InputController;
import com.bot.redessociais.controller.SocialMediaController;
import com.bot.redessociais.model.Input;
import com.bot.redessociais.model.User;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Component
public class SeleniumProvider implements Callable<Processed> {

    private Input input;

    private WebDriver driver;

    private InputController inputController;

    private SocialMediaController socialMediaController;

    @Autowired
    public SeleniumProvider(InputController inputController, SocialMediaController socialMediaController) throws ExecutionException, InterruptedException {
        this.inputController = inputController;
        this.socialMediaController = socialMediaController;
    }

    @Override
    public Processed call() throws Exception {
        FacebookAction facebookAction = new FacebookAction(new SeleniumClient(this.driver,this.inputController,this.socialMediaController));
        return facebookAction.search(this.input);
    }

    public Input getInput() {
        return input;
    }

    public Runnable setInput(Input input) {
        this.input = input;
        return null;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
