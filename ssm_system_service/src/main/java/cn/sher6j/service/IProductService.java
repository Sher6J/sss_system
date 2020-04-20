package cn.sher6j.service;

import cn.sher6j.domain.Product;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-04-18-15:21
 */
public interface IProductService {

    /**
     *
     * @return
     * @throws Exception
     */
    public List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
