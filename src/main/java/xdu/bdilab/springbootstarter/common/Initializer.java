package xdu.bdilab.springbootstarter.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xdu.bdilab.springbootstarter.domain.User;
import xdu.bdilab.springbootstarter.service.UserService;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 密码加密
 * Created by wh on 2019/3/28.
 */
@Service
public class Initializer {
    private Pattern BCRYPT_PATTERN = Pattern
            .compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Object lock = new Object();

    private boolean initialized = false;

    public void initialize(){
        if (!initialized){
            synchronized (lock){
                if (!initialized){
                    initializeUser();
                    initialized = true;
                }
            }
        }
    }

    /**
     * 初始化管理员登录用户名和密码
     */
    private void initializeUser(){
        List<User> users = userService.findAll();
        users.stream()
                .forEach(user -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userService.save(user);
                });
    }

    private boolean judgePasswordEncoded(String password){
        if (BCRYPT_PATTERN.matcher(password).matches()) {
            return true;
        }
        return false;
    }
}
