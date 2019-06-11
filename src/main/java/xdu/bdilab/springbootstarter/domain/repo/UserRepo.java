package xdu.bdilab.springbootstarter.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xdu.bdilab.springbootstarter.domain.User;

/**
 * @author cwz
 * @date 2019/05/10
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
