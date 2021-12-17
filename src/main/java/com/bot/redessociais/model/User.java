package com.bot.redessociais.model;

import lombok.experimental.FieldNameConstants;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.openqa.selenium.WebDriver;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user", schema="social_media")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_social_media_domain")
    private SocialMediaDomain socialMediaDomain;

    @ManyToOne
    @JoinColumn(name = "id_input")
    private Input input;

    private String id_profile;
    private String id_photo;
    private Timestamp created_at;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_profile() {
        return id_profile;
    }

    public void setId_profile(String id_profile) {
        this.id_profile = id_profile;
    }

    public String getId_photo() {
        return id_photo;
    }

    public void setId_photo(String id_photo) {
        this.id_photo = id_photo;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public SocialMediaDomain getSocialMediaDomain() {
        return socialMediaDomain;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public void setSocialMediaDomain(SocialMediaDomain socialMediaDomain) {
        this.socialMediaDomain = socialMediaDomain;
    }

}
