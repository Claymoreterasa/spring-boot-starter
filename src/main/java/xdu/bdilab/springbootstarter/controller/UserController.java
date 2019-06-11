package xdu.bdilab.springbootstarter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xdu.bdilab.springbootstarter.common.Response;
import xdu.bdilab.springbootstarter.domain.User;
import xdu.bdilab.springbootstarter.service.UserService;

/**
 * @author cwz
 * @date 2019/05/16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    @PreAuthorize("hasPermission('/user', 'r')")
    public Response getUser(@PathVariable String username){
        return Response.succeed(userService.findByUsername(username));
    }

    @PostMapping
    @PreAuthorize("hasPermission('/user', 'c')")
    public Response createUser(@RequestBody User user){
        return Response.succeed(userService.save(user));
    }



}
