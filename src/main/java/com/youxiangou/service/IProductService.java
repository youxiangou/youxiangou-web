package com.youxiangou.service;

import com.github.pagehelper.PageInfo;
import com.youxiangou.common.ServerResponse;
import com.youxiangou.pojo.Product;
import com.youxiangou.vo.ProductDetailVo;

/**
 * @Author ljs
 * @Description TODO
 * @Date 2019/2/18 2:00
 **/
public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse setSaleStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse getProductList(int pageNum, int pageSize);

    ServerResponse searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}
