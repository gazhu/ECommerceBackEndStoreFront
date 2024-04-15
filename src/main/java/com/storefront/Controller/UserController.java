package com.storefront.Controller;

import com.storefront.exceptions.UserDoesNotExistException;
import com.storefront.model.User;
import com.storefront.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users/")
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    Logger logger = LoggerFactory.getLogger( UserController.class );

    @GetMapping()
    public List<User> viewAllUser() {
        logger.info( "Request to fetch all users" );
        return userServiceImpl.findAllUsers();
    }

    @PostMapping("add")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        logger.info( "Request to save user" );
        Long userId = user.getUserId();
        if (userId != null && userServiceImpl.findUserById( userId ).getUserId() != null) {
            logger.error( "User already exists, Request cannot be processed" );
            return new ResponseEntity<>( "User " + user.getUserName() + " already exists, Try updating the user",
                    HttpStatus.OK );
        } else {
            int res = userServiceImpl.saveUser( user );
            if (res > 0) {
                logger.trace( "User saved" );
                return new ResponseEntity<>( "User " + user.getUserName() + " added", HttpStatus.OK );
            } else {
                logger.error( "User could not be added" );
                return new ResponseEntity<>( "User " + user.getUserName() + " couldn't be added",
                        HttpStatus.BAD_REQUEST );
            }
        }
    }

    @PostMapping("update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        logger.info( "Request to update user" );
        int res = userServiceImpl.updateUser( user );
        try {
            if (res > 0) {
                logger.trace( "User updated" );
                return new ResponseEntity<>( "User " + user.getUserName() + " has been updated", HttpStatus.OK );
            } else {
                logger.error( "User couldn't be updated" );
                throw new UserDoesNotExistException( "User " + user.getUserName() + " does not exist." );
            }
        }catch (UserDoesNotExistException ex){
            ex.getMessage();
        }
        return new ResponseEntity<>( "User couldn't be updated", HttpStatus.BAD_REQUEST );
    }
}
