package com.bot.redessociais;

import com.bot.redessociais.client.SeleniumClient;
import com.bot.redessociais.client.action.facebook.FacebookAction;
import com.bot.redessociais.client.parser.facebook.FacebookParser;
import com.bot.redessociais.controller.InputController;
import com.bot.redessociais.controller.SocialMediaController;
import com.bot.redessociais.provider.SeleniumProvider;
import com.bot.redessociais.repository.InputRepository;
import com.bot.redessociais.repository.SocialMediaRepository;
import com.bot.redessociais.service.SeleniumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@Configuration
public class RedesSociaisApplication {

	@Autowired
	public InputController inputController;

	@Autowired
	public SocialMediaController socialMediaController;

	@Autowired
	public SeleniumService seleniumService;

	@Autowired
	public InputRepository inputRepository;

	@Autowired
	public SocialMediaRepository socialMediaRepository;

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication application = new SpringApplication(RedesSociaisApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

}
