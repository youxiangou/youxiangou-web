package com.youxiangou.service;

import com.youxiangou.common.ServerResponse;
import com.youxiangou.pojo.Category;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ljs
 * @Description TODO
 * @Date 2019/2/17 22:09
 **/
public interface ICategoryService {

    ServerResponse<String> addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse getChildrenParallelCategory(Integer categoryId);

    ServerResponse selectCategoryAndChildrenById(Integer categoryId);

    ServerResponse deleteCategory(Integer categoryId);
}
