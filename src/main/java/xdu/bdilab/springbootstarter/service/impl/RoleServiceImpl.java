package xdu.bdilab.springbootstarter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xdu.bdilab.springbootstarter.domain.Role;
import xdu.bdilab.springbootstarter.domain.repo.RoleRepo;
import xdu.bdilab.springbootstarter.service.RoleService;

/**
 * @author cwz
 * @date 2019/05/15
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }
}
