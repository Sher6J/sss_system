package cn.sher6j.controller;

import cn.sher6j.domain.SysLog;
import cn.sher6j.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-20-14:27
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll();
        mv.addObject("sysLogs", sysLogList);
        mv.setViewName("syslog-list");
        return mv;
    }
}
