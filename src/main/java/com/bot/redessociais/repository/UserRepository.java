package com.bot.redessociais.repository;

import com.bot.redessociais.model.Input;
import com.bot.redessociais.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "select id from social_media.user where id_profile = :id_profile", nativeQuery = true)
    List<User> getUserByIdProfile(@Param("id_profile") String id_profile);
}
