package xdu.bdilab.springbootstarter.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xdu.bdilab.springbootstarter.domain.Role;

/**
 * @author cwz
 * @date 2019/05/10
 */
public interface RoleRepo extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
