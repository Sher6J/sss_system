package cn.sher6j.service;

import cn.sher6j.domain.SysLog;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-20-14:13
 */
public interface ISysLogService {

    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
