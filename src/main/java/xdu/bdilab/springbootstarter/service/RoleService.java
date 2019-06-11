package xdu.bdilab.springbootstarter.service;

import xdu.bdilab.springbootstarter.domain.Role;

/**
 * @author cwz
 * @date 2019/05/15
 */
public interface RoleService {
    Role findByName(String name);
}
