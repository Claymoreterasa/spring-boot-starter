package xdu.bdilab.springbootstarter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import xdu.bdilab.springbootstarter.common.CodeAndMsg;
import xdu.bdilab.springbootstarter.common.TopException;
import xdu.bdilab.springbootstarter.domain.User;
import xdu.bdilab.springbootstarter.domain.repo.UserRepo;
import xdu.bdilab.springbootstarter.service.UserService;

import java.util.List;

/**
 * @author cwz
 * @date 2019/05/12
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User findByUsername(String username){
        return userRepo
                .findOne(Example.of(User.builder().username(username).build()))
                .orElseThrow(() -> new TopException(CodeAndMsg.user_not_found));
    }

    @Override
    public List<User> findAll(){
        return userRepo.findAll();
    }

    @Override
    public User save(User user){
        return userRepo.save(user);
    }
}
