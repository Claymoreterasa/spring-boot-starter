package xdu.bdilab.springbootstarter.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xdu.bdilab.springbootstarter.domain.Permission;

public interface PermissionRepo extends JpaRepository<Permission, Long> {
}
