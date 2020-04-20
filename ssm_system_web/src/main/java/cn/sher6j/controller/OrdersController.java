package cn.sher6j.controller;

import cn.sher6j.domain.Orders;
import cn.sher6j.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-18-18:32
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    /**
     * 查询全部订单，未分页
     * @return
     * @throws Exception
     */
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        List<Orders> orders = ordersService.findAll();
//        mv.addObject("ordersList", orders);
//        mv.setViewName("orders-list");
//        return mv;
//    }


    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")//@Secured注解方式，不能省略ROLE_前缀，本身就是spring-security中的，不需要单独导入依赖
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
