package cn.sher6j.dao;

import cn.sher6j.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author sher6j
 * @create 2020-04-18-21:06
 */
public interface IMemberDao {

    /**
     * 根据id查询会员
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from member where id=#{id}")
    public Member findById(String id) throws Exception;
}
