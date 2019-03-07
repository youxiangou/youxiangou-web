package com.youxiangou.service;

import com.github.pagehelper.PageInfo;
import com.youxiangou.common.ServerResponse;
import com.youxiangou.pojo.Shipping;
import com.youxiangou.common.ServerResponse;


/**
 * Author ljs
 * Description TODO
 * Date 2019/2/20 0:37
 **/
public interface IShippingService {

    ServerResponse add(Integer userId, Shipping shipping);
    ServerResponse<String> del(Integer userId, Integer shippingId);
    ServerResponse update(Integer userId, Shipping shipping);
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

}
