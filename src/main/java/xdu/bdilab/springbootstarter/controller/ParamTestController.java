package xdu.bdilab.springbootstarter.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xdu.bdilab.springbootstarter.domain.User;
import xdu.bdilab.springbootstarter.domain.repo.UserRepo;

/**
 * @author cwz
 * @date 2019/05/10
 */
@RestController
@RequestMapping("/user")
public class ParamTestController {
    @Autowired
    private UserRepo userRepo;

    /**
     * 传参方式： 1. params 2. form-data 3. x-www-form-urlencoded
     * 传参格式: userId=1
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户详细信息", notes = "no annotation")
    @PostMapping("/infoNoAnnotation")
    public User infoNoAnnotation(Long userId){
        return userRepo.findById(userId).get();
    }

    /**
     * 传参方式： 1. params 2. form-data 3. x-www-form-urlencoded
     * 传参格式: userId=1
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户详细信息", notes = "requestParam")
    @PostMapping("/infoRequestParam")
    public User infoRequestParam(@RequestParam Long userId){
        return userRepo.findById(userId).get();
    }


    /**
     * 传参方式： 1. raw application/json
     * 传参格式: 1
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户详细信息", notes = "requestBody")
    @PostMapping("/infoRequestBody")
    public User infoRequestBody(@RequestBody Long userId){
        return userRepo.findById(userId).get();
    }


    /**
     * 传参方式： 1. params 2. form-data 3. x-www-form-urlencoded
     * 传参格式: username=cwz&password=cwz
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "no annotation")
    @PostMapping("/createNoAnnotation")
    public User createNoAnnotation(User user){
        return userRepo.save(user);
    }

    /**
     * 都不行？
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "requestParam")
    @PostMapping("/createRequestParam")
    public User creatRequestParam(@RequestParam User user){
        return userRepo.save(user);
    }

    /**
     * 传参方式：  1. raw application/json
     * 传参格式: {"username":"cwz","password":"cwz"}
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "requestBody")
    @PostMapping("/createRequestBody")
    public User createRequestBody(@RequestBody User user){
        return userRepo.save(user);
    }

    /**
     * 都不行
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "requestBody")
    @PostMapping("/createRequestBodyParam")
    public User createRequestBodyParam(@RequestBody String username, @RequestBody String password){
        System.out.println(username);
        return userRepo.save(User.builder().username(username).password(password).build());
    }
}
