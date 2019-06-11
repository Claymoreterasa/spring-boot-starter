package xdu.bdilab.springbootstarter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xdu.bdilab.springbootstarter.common.TopException;
import xdu.bdilab.springbootstarter.domain.User;
import xdu.bdilab.springbootstarter.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cwz
 * @date 2019/05/12
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User sysUser;
        try{
            sysUser = userService.findByUsername(username);
        }catch (TopException e){
            throw new UsernameNotFoundException(username);
        }

        List<SimpleGrantedAuthority> authorities = sysUser.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }
}
