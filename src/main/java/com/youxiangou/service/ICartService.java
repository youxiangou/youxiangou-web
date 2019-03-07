package com.youxiangou.service;

import com.youxiangou.common.ServerResponse;
import com.youxiangou.vo.CartVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ljs
 * @Description TODO
 * @Date 2019/2/19 3:46
 **/
public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct( Integer id, String productIds);

    ServerResponse<CartVo> list (Integer userId);

    ServerResponse<CartVo> selectOrUnSelect ( Integer userId, Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer id);
}
