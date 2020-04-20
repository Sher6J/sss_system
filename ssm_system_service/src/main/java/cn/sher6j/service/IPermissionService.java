package cn.sher6j.service;

import cn.sher6j.domain.Permission;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-19:31
 */
public interface IPermissionService {

    public List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(String id) throws Exception;

    void deleteById(String id) throws Exception;
}
