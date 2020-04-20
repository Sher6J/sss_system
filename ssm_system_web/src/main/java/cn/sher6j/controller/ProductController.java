package cn.sher6j.controller;

import cn.sher6j.domain.Product;
import cn.sher6j.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-18-15:35
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询全部产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")//JSR-250注解方式控制权限，能省略ROLE_前缀，需要单独导入依赖
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList", products);
        mv.setViewName("product-list1");//指定视图到product-list页面
        return mv;
    }


    /**
     * 产品添加
     * @param product
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

}
