package com.bot.redessociais.repository;

import com.bot.redessociais.model.SocialMediaDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaRepository extends CrudRepository<SocialMediaDomain, Long> {

    @Query(value = "select id,name,created_at from social_media.social_media_domain where id = :id", nativeQuery = true)
    SocialMediaDomain getSocialMediaDomainById(@Param("id") Long id);

}
