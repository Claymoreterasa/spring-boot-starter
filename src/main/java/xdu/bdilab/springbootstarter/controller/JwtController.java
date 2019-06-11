package xdu.bdilab.springbootstarter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xdu.bdilab.springbootstarter.common.CodeAndMsg;
import xdu.bdilab.springbootstarter.common.Response;
import xdu.bdilab.springbootstarter.domain.User;
import xdu.bdilab.springbootstarter.service.UserService;
import xdu.bdilab.springbootstarter.utils.JwtUtils;

/**
 * @author cwz
 * @date 2019/05/13
 */
@RestController
@RequestMapping("/token")
public class JwtController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/")
    public Response token(String username, String password){
        User user = userService.findByUsername(username);
        if(passwordEncoder.matches(password, user.getPassword())){
            return Response.succeed(CodeAndMsg.login_succeed, jwtUtils.generateAccessToken(user));
        }else{
            return Response.failed(CodeAndMsg.authorization_failed);
        }
    }
}
