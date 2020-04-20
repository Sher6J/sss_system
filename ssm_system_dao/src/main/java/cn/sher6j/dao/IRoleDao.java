package cn.sher6j.dao;

import cn.sher6j.domain.Permission;
import cn.sher6j.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-10:47
 */
public interface IRoleDao {

    /**
     * 根据用户Id查询所有对应角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "cn.sher6j.dao.IPermissionDao.findPermissionByRoleId")),
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;


    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "cn.sher6j.dao.IPermissionDao.findPermissionByRoleId")),
    })
    Role findById(String roleId) throws Exception;

    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId);

    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId);

    @Delete("delete from role where id=#{roleId}")
    void deleteRoleById(String roleId);

    /**
     * 查询当前角色没有关联的权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission (roleId, permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
