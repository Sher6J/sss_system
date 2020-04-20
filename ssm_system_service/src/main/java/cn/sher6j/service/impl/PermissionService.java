package cn.sher6j.service.impl;

import cn.sher6j.dao.IPermissionDao;
import cn.sher6j.domain.Permission;
import cn.sher6j.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-19:32
 */
@Service
@Transactional
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void deleteById(String id) throws Exception {
        permissionDao.deleteFromRole_PermissionById(id);
        permissionDao.deleteById(id);
    }
}
