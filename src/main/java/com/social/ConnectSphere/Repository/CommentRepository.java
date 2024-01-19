package com.social.ConnectSphere.Repository;

import com.social.ConnectSphere.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
