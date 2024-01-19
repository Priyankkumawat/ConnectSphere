package com.social.ConnectSphere.Service.ServiceImpl;

import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Model.User;
import com.social.ConnectSphere.Payload.UserDTO;
import com.social.ConnectSphere.Repository.UserRepository;
import com.social.ConnectSphere.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userFromDTO(userDTO);
        User savedUser = userRepository.save(user);
        return userDTOFromUser(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userDTO.userName));
        if (userDTO.userName != null) user.setUserName(userDTO.userName);
        if (userDTO.password != null) user.setPassword(userDTO.password);
        if (userDTO.email != null) user.setEmail(userDTO.email);
        User savedUser = userRepository.save(user);
        return userDTOFromUser(savedUser);
    }

    @Override
    public UserDTO getUserByUsername(String username) throws ResourceNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new ResourceNotFoundException("User", "Id", username);
        }
        return userDTOFromUser(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTO = users.stream().map(this::userDTOFromUser).collect(Collectors.toList());
        return userDTO;
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        userRepository.deleteById(user.getId());
    }

    private User userFromDTO(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO userDTOFromUser(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
