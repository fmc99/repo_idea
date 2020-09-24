package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {

    /*
    查询所有资源分类
 */
    public List<ResourceCategory> findAllResourceCategory();

    /**
     * 描述: 修改资源分类接口
     * @param resourceCategory
     */
    public void saveResourceCategory(ResourceCategory resourceCategory);

    /**
     * 修改源分类接口
     * @param resourceCategory
     */
    public void updateResourceCategory(ResourceCategory resourceCategory);

    /**
     * 根据id删除资源分类
     * @param id
     */
    public void deleteResourceCategory(Integer id);
}
