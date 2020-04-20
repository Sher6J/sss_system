package cn.sher6j.dao;

import cn.sher6j.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-20-14:14
 */
public interface ISysLogDao {

    /**
     * 向数据库中添加日志
     * @param sysLog
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;

    @Select("select * from syslog")
    List<SysLog> findAll() throws Exception;
}
