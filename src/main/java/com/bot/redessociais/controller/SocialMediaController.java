package com.bot.redessociais.controller;

import com.bot.redessociais.model.SocialMediaDomain;
import com.bot.redessociais.repository.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ExecutionException;

@Controller
public class SocialMediaController {

    private SocialMediaRepository socialMediaRepository;

    @Autowired
    public void initialize(SocialMediaRepository socialMediaRepository) throws ExecutionException, InterruptedException {
        this.socialMediaRepository = socialMediaRepository;
    }

    public SocialMediaDomain getRedeSocialById(Long id) {
        return socialMediaRepository.getSocialMediaDomainById(id);
    }
}
