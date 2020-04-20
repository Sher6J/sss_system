package cn.sher6j.service;

import cn.sher6j.domain.Role;
import cn.sher6j.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-10:13
 */
public interface IUserService extends UserDetailsService {
    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRoles(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
