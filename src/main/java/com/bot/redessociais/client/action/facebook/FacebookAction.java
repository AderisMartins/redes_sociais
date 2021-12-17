package com.bot.redessociais.client.action.facebook;

import com.bot.redessociais.client.Processed;
import com.bot.redessociais.client.SeleniumClient;
import com.bot.redessociais.client.XpathEnum;
import com.bot.redessociais.client.parser.facebook.FacebookParser;
import com.bot.redessociais.model.Input;

import java.io.IOException;

public class FacebookAction {

    private SeleniumClient seleniumClient;

    public FacebookAction(SeleniumClient seleniumClient) throws IOException {
        this.seleniumClient = seleniumClient;
    }

    public Processed search(Input input) throws IOException, InterruptedException {
        this.seleniumClient.navigate(this.seleniumClient.getConfig().getUrl());
        if(this.seleniumClient.getTitle().contains("entre") || this.seleniumClient.getTitle().contains("enter")) {
            this.seleniumClient.sendKeys(this.seleniumClient.waitForAnGetElementClickable(XpathEnum.FACEBOOK_INPUT_LOGIN.getXpath()), this.seleniumClient.getConfig().getFacebook_login());
            this.seleniumClient.sendKeys(this.seleniumClient.waitForAnGetElementClickable(XpathEnum.FACEBOOK_INPUT_PASSWORD.getXpath()), this.seleniumClient.getConfig().getFacebook_password());
            this.seleniumClient.click(this.seleniumClient.waitForAnGetElementClickable(XpathEnum.FACEBOOK_BUTTON_LOGIN.getXpath()));
        }
        this.seleniumClient.sendKeys(this.seleniumClient.waitForAnGetElementClickable(XpathEnum.FACEBOOK_INPUT_SEARCH.getXpath()), input.getEmail() + " " + input.getName());
        this.seleniumClient.click(this.seleniumClient.waitForAnGetElementClickable(XpathEnum.FACEBOOK_BUTTON_SEARCH.getXpath()));
        this.seleniumClient.click(this.seleniumClient.waitForAnGetElementClickable(XpathEnum.FACEBOOK_BUTTON_SEEALL.getXpath()));
        return new FacebookParser(this.seleniumClient).getProcessed(input);
    }

}
