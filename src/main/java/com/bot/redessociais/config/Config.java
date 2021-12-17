package com.bot.redessociais.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Config {

    private String url = "https://www.facebook.com";
    private int timeOut = 60000;
    private int timeOutElementExists = 15000;
    private String chromedriver_path;
    private List<String> chromeOptions;
    private int searchAttempts;
    private String facebook_login;
    private String facebook_password;
    private double minSimilarity;
    private int minTimeStandBy;
    private int maxTimeStandBy;

    public Config getConfig() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder json = new StringBuilder();
        FileReader fileReader = new FileReader("config.json");
        try {
            char [] chars = new char[2048];
            fileReader.read(chars);

            for(char line : chars) {
                json.append(line);
            }
        } finally {
            fileReader.close();
        }
        return objectMapper.readValue(json.toString(), Config.class);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getChromeOptions() {
        return chromeOptions;
    }

    public void setChromeOptions(List<String> chromeOptions) {
        this.chromeOptions = chromeOptions;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getTimeOutElementExists() {
        return timeOutElementExists/1000;
    }

    public void setTimeOutElementExists(int timeOutElementExists) {
        this.timeOutElementExists = timeOutElementExists;
    }

    public int getSearchAttempts() {
        return searchAttempts;
    }

    public void setSearchAttempts(int searchAttempts) {
        this.searchAttempts = searchAttempts;
    }

    public String getFacebook_login() {
        return facebook_login;
    }

    public void setFacebook_login(String facebook_login) {
        this.facebook_login = facebook_login;
    }

    public String getFacebook_password() {
        return facebook_password;
    }

    public void setFacebook_password(String facebook_password) {
        this.facebook_password = facebook_password;
    }

    public String getChromedriver_path() { return chromedriver_path; }

    public void setChromedriver_path(String chromedriver_path) { this.chromedriver_path = chromedriver_path; }

    public double getMinSimilarity() { return minSimilarity; }

    public void setMinSimilarity(double minSimilarity) { this.minSimilarity = minSimilarity; }

    public int getMinTimeStandBy() { return minTimeStandBy; }

    public void setMinTimeStandBy(int minTimeStandBy) { this.minTimeStandBy = minTimeStandBy; }

    public int getMaxTimeStandBy() { return maxTimeStandBy; }

    public void setMaxTimeStandBy(int maxTimeStandBy) {
        this.maxTimeStandBy = maxTimeStandBy;
    }
}
