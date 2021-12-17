package com.bot.redessociais.client;

public enum XpathEnum {
    FACEBOOK_INPUT_LOGIN("//input[@id='email']"),
    FACEBOOK_INPUT_PASSWORD("//input[@id='pass']"),
    FACEBOOK_BUTTON_LOGIN("//button[@name='login']"),
    FACEBOOK_INPUT_SEARCH("//input[@type='search']"),
    FACEBOOK_BUTTON_SEARCH("//*[contains(text(),'Pesquisar')]"),
    FACEBOOK_BUTTON_SEEALL("(//a[contains(@href,'search/people')])[1]"),
    FACEBOOK_PEOPLES("//div[@role='article']"),
    FACEBOOK_LINK_PHOTO("//a[contains(@href,'facebook.com/photo')]"),
    FACEBOOK_SPAN_NOTFOUND("//span[contains(text(),'nenhum')]");

    private String xpath;

    public String getXpath() {
        return xpath;
    }

    XpathEnum(String id) { this.xpath = id; }

    public void setXpath(String redeSocial) {
        this.xpath = redeSocial;
    }
}
