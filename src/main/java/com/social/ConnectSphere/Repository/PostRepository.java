package com.social.ConnectSphere.Repository;

import com.social.ConnectSphere.Model.Post;
import com.social.ConnectSphere.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);

    //Customize query same can be done by adding Containing at end of content
    @Query("select p from Post p where p.content like :keyword")
    List<Post> findByContent( String keyword);
}