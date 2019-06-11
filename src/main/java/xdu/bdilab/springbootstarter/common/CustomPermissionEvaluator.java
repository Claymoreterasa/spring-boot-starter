package xdu.bdilab.springbootstarter.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xdu.bdilab.springbootstarter.domain.Permission;
import xdu.bdilab.springbootstarter.domain.Role;
import xdu.bdilab.springbootstarter.service.RoleService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author cwz
 * @date 2019/05/15
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator{
    @Autowired
    private RoleService roleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for(GrantedAuthority authority : authorities){
            String roleName = authority.getAuthority();
            Role role = roleService.findByName(roleName);

            List<Permission> permissions = role.getPermissions();
            for(Permission ownedPermission : permissions){
                String allowedOperations = ownedPermission.getAllowedOperations();
                List<String> allowedOperationsList = Arrays.asList(allowedOperations.split("\\s+"));
                if(pathMatcher.match(ownedPermission.getUrl(), targetUrl.toString()) && allowedOperationsList.contains(targetPermission))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

}
