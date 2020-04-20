package cn.sher6j.controller;

import cn.sher6j.domain.Permission;
import cn.sher6j.domain.Role;
import cn.sher6j.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-17:46
 */
@RequestMapping("/role")
@Controller
public class RoleContrloller {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 保存角色
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteRole.do")
    public String deleteRole(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        roleService.deleteRoleById(roleId);
        return "redirect:findAll.do";
    }

    /**
     * 根据roleId查询role，并查询出可以添加到权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据roleId查询role
        Role role = roleService.findById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     * @return
     * @throws Exception
     */
    @RequestMapping("addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId,
                                    @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }

}
