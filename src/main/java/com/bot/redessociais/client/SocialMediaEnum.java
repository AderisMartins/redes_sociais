package com.bot.redessociais.client;

public enum SocialMediaEnum {
    FACEBOOK(1),
    INSTAGRAM(2);

    private int id;

    public long getId() {
        return Long.valueOf(id);
    }

    SocialMediaEnum(int id) { this.id = id; }

    public void setId(int redeSocial) {
        this.id = redeSocial;
    }
}
