package cn.sher6j.controller;

import cn.sher6j.domain.Role;
import cn.sher6j.domain.UserInfo;
import cn.sher6j.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-19-11:36
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")//支持表达式的注解@PreAuthorize
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 添加用户操作
     * @param userInfo
     */
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'sher6j'")//只有sher6j可以完成添加操作
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }


    /**
     * 查询指定id的用户
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 根据用户id查询其可以添加的角色
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);
        //2.根据用户id查询用户可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     *
     * @param userId 用户id
     * @param roleIds 可以添加的角色id
     */
    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId,
                              @RequestParam(name = "ids", required = true) String[] roleIds) throws Exception{
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
