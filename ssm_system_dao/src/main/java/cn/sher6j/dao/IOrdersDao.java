package cn.sher6j.dao;

import cn.sher6j.domain.Member;
import cn.sher6j.domain.Orders;
import cn.sher6j.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-18-18:35
 */
public interface IOrdersDao {

    /**
     * 查询所有订单
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNumber", column = "orderNumber"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "cn.sher6j.dao.IProductDao.findById")),
    })
    public List<Orders> findAll() throws Exception;


    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "cn.sher6j.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,
                    one = @One(select = "cn.sher6j.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,
                    many = @Many(select = "cn.sher6j.dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId) throws Exception;
}
