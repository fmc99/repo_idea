package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {

    /*
        查询所有角色（条件）
     */
    public List<Role>  findAllRole(Role role);

    /*
    根据角色ID查询该角色关联的菜单信息ID [1,2,3,5]
 */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
        为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
        删除角色
     */
    public void deleteRole(Integer roleid);
    /*
    根据角色id查询当前角色拥有的资源分类信息
     */
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer roleid);
    /*
    根据资源分类id查询对应得资源
     */
    public List<Resource> findResourceByCategoryId(Integer CategoryId);

/*    *//*
根据角色ID 删除角色与资源的关联关系(操作中中间表 role_resource_relation)
 *//*
    public void deleteRoleResourceRelationByRoleid(Integer roleId);
    *//*
    为角色分配资源, 删除完成后 ,插入最新的关联关系
     *//*
    public void saveRoleResourceRelation(RoleResourceRelation resourceRelation);*/

    public void roleContextResource(RoleResourceVo resourceVo);
}
