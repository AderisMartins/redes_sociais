package com.bot.redessociais.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "input", schema="social_media")
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String city;
    private int flag_process;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getFlag_process() { return flag_process; }

    public void setFlag_process(int flag_process) { this.flag_process = flag_process; }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() { return updated_at; }

    public void setUpdated_at(LocalDateTime updated_at) { this.updated_at = updated_at; }
}
