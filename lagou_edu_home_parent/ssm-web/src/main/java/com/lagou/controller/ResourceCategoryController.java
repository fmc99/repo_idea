package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        return new ResponseResult(true, 200, "查询所有分类信息成功", allResourceCategory);

    }

    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory) {
        if (resourceCategory.getId() == null ) {
            //封装补充数据
            Date date =new Date();
            resourceCategory.setCreatedTime(date);
            resourceCategory.setUpdatedTime(date);
            resourceCategory.setCreatedBy("system");
            resourceCategory.setUpdatedBy("system");
            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true, 200, "添加资源成功", null);
        }else {
            //封装补充数据
            Date date =new Date();
            resourceCategory.setUpdatedTime(date);
            resourceCategory.setCreatedBy("system");
            resourceCategory.setUpdatedBy("system");
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true, 200, "修改资源成功", null);
        }
    }
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategory(id);
        return new ResponseResult(true, 200, "删除资源分类成功", null);
    }
}
