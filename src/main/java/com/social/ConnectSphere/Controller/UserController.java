package com.social.ConnectSphere.Controller;

import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Payload.UserDTO;
import com.social.ConnectSphere.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //Post-user
    @PostMapping("/add")
    public ResponseEntity<?> creatUser( @RequestBody UserDTO userDTO){
        UserDTO userDTO1 = userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
    }

    //Update-user
    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer userId){
        try{
            UserDTO userDTO1 = userService.updateUser(userDTO,userId);
            return new ResponseEntity<>(userDTO1,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //Get-user
    @GetMapping("/get/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) throws ResourceNotFoundException {
        UserDTO userDTO = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
    }

    //Get-all-user
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.FOUND);
    }

    //Delete-user
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String username){
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>("User is deleted",HttpStatus.GONE);
        //return new ResponseEntity<>("deleted", HttpStatus.GONE);
    }
}