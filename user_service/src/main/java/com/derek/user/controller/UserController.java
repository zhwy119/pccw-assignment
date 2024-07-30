package com.derek.user.controller;

import com.derek.common.constants.ResultCodes;
import com.derek.common.vo.RestResult;
import com.derek.email.service.EmailFeignService;
import com.derek.user.entity.User;
import com.derek.user.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserController is the REST controller for managing user operations.
 */
@Api(tags = "User Service Interfaces")
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    public static final String DEL_FLAG_DELETED = "1";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param user The user to register.
     * @return RestResult indicating the result of the operation.
     * @throws Exception if any error occurs during user registration.
     */
    @PostMapping
    @ApiOperation(value = "Register a new user", notes = "Creates a new user")
    public RestResult createUser(@Valid @RequestBody User user) throws Exception {
        try {
            userService.save(user);
            // Call send email asynchronously
            userService.sendEmailForUser(user);
        } catch (Exception e) {
            log.error("error happened when create a user", e);
            return RestResult.error();
        }
        return RestResult.success();
    }

    /**
     * Retrieves the list of all users.
     *
     * @return RestResult containing the list of users.
     */
    @GetMapping
    @ApiOperation(value = "Get list of users", notes = "Returns a list of all users")
    public RestResult list() {
        RestResult restResult = RestResult.success();
        try {
            List<User> userList = userService.listUsers();
            restResult.setObject(userList);
        } catch (Exception e) {
            log.error("error happened when create a user", e);
            return RestResult.error();
        }
        return restResult;
    }

    /**
     * Updates an existing user.
     *
     * @param id   The ID of the user to update.
     * @param user The user data to update.
     * @return RestResult indicating the result of the operation.
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a user", notes = "Updates an existing user")
    public RestResult updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        if (id == null) {
            return RestResult.fail(ResultCodes.FAILURE, "User ID is required");
        }
        try {
            user.setId(id);
            userService.updateById(user);
        } catch (Exception e) {
            return RestResult.error();
        }
        return RestResult.success();
    }

    /**
     * Deactivates an existing user.
     *
     * @param id The ID of the user to deactivate.
     * @return RestResult indicating the result of the operation.
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user", notes = "Deactivates an existing user")
    public RestResult deactivateUser(@PathVariable Long id) {
        if (id == null) {
            return RestResult.fail(ResultCodes.FAILURE, "User ID is required");
        }
        try {
            User user = userService.getById(id);
            user.setDelFlag(DEL_FLAG_DELETED);
            userService.updateById(user);
        } catch (Exception e) {
            return RestResult.error();
        }
        return RestResult.success();
    }
}
