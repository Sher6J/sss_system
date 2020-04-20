package cn.sher6j.controller;

import cn.sher6j.domain.Permission;
import cn.sher6j.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-19:22
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        Permission permission = permissionService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("permission", permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("deletePermission")
    public String deletePermission(String id) throws Exception {
        permissionService.deleteById(id);
        return "redirect:findAll.do";
    }

}
