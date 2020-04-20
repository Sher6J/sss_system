package cn.sher6j.dao;

import cn.sher6j.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-12:45
 */
public interface IPermissionDao {

    /**
     * 通过角色id查询对应权限
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    /**
     * 添加权限
     * @param permission
     * @throws Exception
     */
    @Insert("insert into permission(permissionName, url)values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    /**
     * 根据id查询权限
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id=#{id}")
    Permission findById(String id) throws Exception;

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_PermissionById(String id);

    @Delete("delete from permission where id=#{id}")
    void deleteById(String id);
}
