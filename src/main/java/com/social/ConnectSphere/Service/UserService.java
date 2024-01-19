package com.social.ConnectSphere.Service;

import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Payload.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, Integer id) throws ResourceNotFoundException;
    UserDTO getUserByUsername(String username) throws ResourceNotFoundException;
    List<UserDTO> getAllUser();
    void deleteUserByUsername(String username);
}