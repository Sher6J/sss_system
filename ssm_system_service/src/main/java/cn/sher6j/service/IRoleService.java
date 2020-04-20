package cn.sher6j.service;

import cn.sher6j.domain.Permission;
import cn.sher6j.domain.Role;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-17:48
 */
public interface IRoleService {

    public List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;


    Role findById(String roleId) throws Exception;


    void deleteRoleById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
