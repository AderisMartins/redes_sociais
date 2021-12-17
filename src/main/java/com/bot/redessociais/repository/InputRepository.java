package com.bot.redessociais.repository;

import com.bot.redessociais.model.Input;
import com.bot.redessociais.model.SocialMediaDomain;
import com.bot.redessociais.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputRepository extends CrudRepository<Input, Long> {

    @Query(value = "update social_media.input set flag_process = 1, updated_at = now() where id in (select id from social_media.input where flag_process = 0 limit 1) returning *;",nativeQuery = true)
    List<Input> findAllInputs();

    @Query(value = "select id,email,name,city,flag_process,created_at,updated_at from social_media.input where id = :id", nativeQuery = true)
    Input getInputById(@Param("id") Long id);


}
