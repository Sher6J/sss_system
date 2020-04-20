package cn.sher6j.service;

import cn.sher6j.domain.Orders;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-18-18:33
 */
public interface IOrdersService {

    /**
     * 查询所有订单
     * @return
     * @throws Exception
     */
    List<Orders> findAll(int page, int size) throws Exception;

    /**
     * 根据Id查询订单
     * @param ordersId
     * @return
     * @throws Exception
     */
    Orders findById(String ordersId) throws Exception;
}
