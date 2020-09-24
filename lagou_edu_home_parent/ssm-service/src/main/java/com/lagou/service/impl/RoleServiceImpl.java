package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);

        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1. 清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2. 为角色分配菜单

        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");


            roleMapper.roleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleid) {

        // 调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer roleid) {
        List<ResourceCategory> resourceCategoryByRoleId = roleMapper.findResourceCategoryByRoleId(roleid);
        return resourceCategoryByRoleId;
    }

    @Override
    public List<Resource> findResourceByCategoryId(Integer CategoryId) {
        List<Resource> resourceByCategoryId = roleMapper.findResourceByCategoryId(CategoryId);
        return resourceByCategoryId;
    }

/*    @Override
    public void deleteRoleResourceRelationByRoleid(Integer roleId) {
        roleMapper.deleteRoleResourceRelationByRoleid(roleId);
    }

    @Override
    public void saveRoleResourceRelation(RoleResourceRelation resourceRelation) {
        roleMapper.saveRoleResourceRelation(resourceRelation);
    }*/

    @Override
    public void roleContextResource(RoleResourceVo resourceVo) {
        Integer roleId = resourceVo.getRoleId();
        List<Integer> resourceIdList = resourceVo.getResourceIdList();
        //删除中间表
        roleMapper.deleteRoleResourceRelationByRoleid(roleId);
        RoleResourceRelation roleResourceRelation=new RoleResourceRelation();
        //遍历添加新关系
        if (resourceIdList!=null){
            for (Integer resourceId : resourceIdList) {
                roleResourceRelation.setRoleId(roleId);
                roleResourceRelation.setResourceId(resourceId);
                Date date=new Date();
                roleResourceRelation.setCreatedTime(date);
                roleResourceRelation.setUpdatedTime(date);
                roleResourceRelation.setCreatedBy("system");
                roleResourceRelation.setUpdatedBy("system");
                roleMapper.saveRoleResourceRelation(roleResourceRelation);
            }
        }
    }
}
