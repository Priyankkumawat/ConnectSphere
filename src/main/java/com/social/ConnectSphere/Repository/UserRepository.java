package com.social.ConnectSphere.Repository;

import com.social.ConnectSphere.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
}