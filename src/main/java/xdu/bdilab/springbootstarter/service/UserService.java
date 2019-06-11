package xdu.bdilab.springbootstarter.service;

import xdu.bdilab.springbootstarter.domain.User;

import java.util.List;

/**
 * @author cwz
 * @date 2019/05/12
 */
public interface UserService {
    User findByUsername(String username);
    List<User> findAll();
    User save(User user);
}
