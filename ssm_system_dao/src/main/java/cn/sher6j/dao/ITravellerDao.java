package cn.sher6j.dao;

import cn.sher6j.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-18-21:09
 */
public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId) throws Exception;

}
