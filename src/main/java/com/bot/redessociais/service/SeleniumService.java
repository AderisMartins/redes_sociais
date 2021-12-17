package com.bot.redessociais.service;

import com.bot.redessociais.client.Processed;
import com.bot.redessociais.client.SeleniumClient;
import com.bot.redessociais.client.SeleniumDriver;
import com.bot.redessociais.client.action.facebook.FacebookAction;
import com.bot.redessociais.config.Config;
import com.bot.redessociais.controller.InputController;
import com.bot.redessociais.controller.SocialMediaController;
import com.bot.redessociais.controller.UserController;
import com.bot.redessociais.model.Input;
import com.bot.redessociais.provider.SeleniumProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SeleniumService {

    private SeleniumProvider seleniumProvider;

    private InputController inputController;

    private SocialMediaController socialMediaController;

    private Config config;

    private static final ExecutorService threadpool = Executors.newFixedThreadPool(1);

    public static final Logger logger = LogManager.getLogger(SeleniumClient.class);

    private List<SeleniumDriver> seleniumDriverList;

    @Autowired
    public SeleniumService(SeleniumProvider seleniumProvider, InputController inputController, SocialMediaController socialMediaController) throws ExecutionException, InterruptedException, IOException {
        this.seleniumProvider = seleniumProvider;
        this.inputController = inputController;
        this.socialMediaController = socialMediaController;
        this.config = new Config().getConfig();
        process();
    }

    public void process() throws ExecutionException, InterruptedException, IOException {
        while (true) {
            try {
                if(seleniumDriverList==null)
                    seleniumDriverList = new ArrayList<SeleniumDriver>();
                for (Input input : inputController.getInputList()) {
                    String email = "";
                    SeleniumDriver seleniumDriver = new SeleniumDriver(input.getId(), getDriverNotUsed());
                    try {
                        logger.info(String.format("Searching for e-mail => %s ", input.getEmail()));

                        seleniumDriverList.add(seleniumDriver);

                        FacebookAction facebookAction = new FacebookAction(new SeleniumClient(seleniumDriver.getDriver(),this.inputController,this.socialMediaController));
                        Processed processed = facebookAction.search(input);
                        setDriverNotUsedById(processed.getInput().getId());
                        //Not found
                        if (processed.getUser() == null || processed.getUser().getId_profile() == null) {
                            inputController.UpdateFlagProcess(processed.getInput().getId(), 5);
                        } else { //Success
                            email = processed.getInput().getEmail();
                            logger.info(String.format("E-mail => %s found succesfuly ", processed.getInput().getEmail()));
                            UserController.insertUserSuccesfuly(processed.getUser());
                            inputController.UpdateFlagProcess(processed.getInput().getId(), 2);
                        }
                    } catch (DataIntegrityViolationException ex) {
                        logger.info(String.format("User %s already exists",email));
                        if (seleniumDriver != null)
                            seleniumDriver.setInUse(false);
                    }
                    long wait = (long) (config.getMinTimeStandBy() + Math.random() * config.getMaxTimeStandBy());
                    logger.info(String.format("Waiting for %d milliseconds...",wait));
                    Thread.sleep(wait);
                }

            } catch (Exception ex) {
                logger.error(ex.toString());
                setAllDriversNotUsed();
            }
        }
    }

    private WebDriver getDriverNotUsed(){
        if(seleniumDriverList==null)
            return null;
        for(SeleniumDriver seleniumDriver : seleniumDriverList) {
            if(seleniumDriver.isInUse()==false)
                return seleniumDriver.getDriver();
        }
        return null;
    }

    private void setDriverNotUsedById(long id) {
        if(seleniumDriverList==null)
            return;
        for(SeleniumDriver seleniumDriver : seleniumDriverList) {
            if(seleniumDriver.getId() == id)
                seleniumDriver.setInUse(false);
        }
    }

    private void setAllDriversNotUsed() {
        if(seleniumDriverList==null)
            return;
        for(SeleniumDriver seleniumDriver : seleniumDriverList) {
            seleniumDriver.setInUse(false);
        }
    }

}
