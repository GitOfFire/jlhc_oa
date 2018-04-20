package com.jlhc.oa.service;

import com.jlhc.oa.dto.function.FuncRoleRelation;
import com.jlhc.oa.dto.role.Role;
import com.jlhc.oa.dto.role.RoleIdAndUserIds;
import com.jlhc.oa.dto.role.UserIdAndRoleIds;
import java.util.List;

public interface RoleService {
    public List<Role> queryAllRoles();

    public Integer createRole(Role role);

    public Integer reworkBaseRole(Role role);

    public List<Role> queryRolesByUserId(Integer userId);

    public List<Role> queryRolesByGroupId(Integer roleGroupId);

    public Integer createRoleRelationsToUser(Integer userId, List<Integer> roleIds);

    public Integer createUserRelationsToRole(Integer roleId, List<Integer> userIds);

    Integer dropRoleUserRelationOfRole(RoleIdAndUserIds roleIdAndUserIds);

    Integer dropRoleUserRelationWithOfUser(UserIdAndRoleIds userIdAndRoleIds);

    Integer dropRole(Integer roleId);

    Role queryRoleInfoById(Integer roleId);

    Boolean hasDefRoleData();
}
