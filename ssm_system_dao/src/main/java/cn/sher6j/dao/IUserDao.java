package cn.sher6j.dao;

import cn.sher6j.domain.Role;
import cn.sher6j.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-10:16
 */
public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "cn.sher6j.dao.IRoleDao.findRoleByUserId")),
    })
    public UserInfo findByUsername(String username) throws Exception;

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /**
     * 添加用户
     * @param userInfo
     * @throws Exception
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据id查询用户信息，包括用户的角色信息，和角色拥有的权限信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "cn.sher6j.dao.IRoleDao.findRoleByUserId")),
    })
    UserInfo findById(String id) throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId) throws Exception;

    @Insert("insert into users_role (userId, roleId) values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
