package com.bot.redessociais.client.parser.facebook;

import com.bot.redessociais.client.Processed;
import com.bot.redessociais.client.SocialMediaEnum;
import com.bot.redessociais.client.SeleniumClient;
import com.bot.redessociais.client.XpathEnum;
import com.bot.redessociais.controller.InputController;
import com.bot.redessociais.controller.SocialMediaController;
import com.bot.redessociais.model.Input;
import com.bot.redessociais.model.User;
import com.bot.redessociais.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class FacebookParser {

    private SeleniumClient seleniumClient;

    private static final SocialMediaEnum redeSocial = SocialMediaEnum.FACEBOOK;

    public FacebookParser(SeleniumClient seleniumClient) throws IOException {
        this.seleniumClient = seleniumClient;
    }

    public Processed getProcessed(Input input) throws IOException, InterruptedException {
        User user = new User();
        Thread.sleep(5000);
        Iterator<WebElement> elements = this.seleniumClient.getElementList(XpathEnum.FACEBOOK_PEOPLES.getXpath());
        if(elements==null)
            return new Processed(input,null,this.seleniumClient.getDriver());
        while(elements.hasNext()) {
            WebElement element = elements.next();
            String nome = element.getText().indexOf("\n") !=-1 ? element.getText().split("\\n")[0] : element.getText();
            double similarity = StringUtil.similarity(input.getName().toUpperCase(),nome.toUpperCase());
            if(similarity >= this.seleniumClient.getConfig().getMinSimilarity() ||  (input.getCity()!=null && element.getText().toString().toUpperCase().contains(input.getCity().toUpperCase()))) {
                List<WebElement> links = element.findElements(By.cssSelector("svg > g> image"));
                for(WebElement link : links) {
                    link.click();
                }
                String url = this.seleniumClient.getAttribute(XpathEnum.FACEBOOK_LINK_PHOTO.getXpath(), "href");
                user.setId_photo(getIdPhoto(url));
                user.setId_profile(getIdProfile(url));
                user.setSocialMediaDomain(this.seleniumClient.getSocialMediaController().getRedeSocialById(redeSocial.getId()));
                user.setInput(this.seleniumClient.getInputController().getInpuById(input.getId()));
                user.setCreated_at(new Timestamp(System.currentTimeMillis()));
                return new Processed(input,user,this.seleniumClient.getDriver());
            }
        }
        return new Processed(input,user,this.seleniumClient.getDriver());
    }

    private String getIdPhoto(String url) {
        String[] variables = url.split("=");
        if(variables.length>0) {
            return variables[1].replace("&set","").replaceAll("\\D+","");
        }
        return null;
    }

    private String getIdProfile(String url) {
        String[] variables = url.split("=");
        if(variables.length>0) {
            return variables[2].replace("a.","").replaceAll("\\D+","");
        }
        return null;
    }


}
