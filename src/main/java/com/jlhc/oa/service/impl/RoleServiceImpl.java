package com.jlhc.oa.service.impl;

import com.jlhc.oa.dao.RoleGroupMapper;
import com.jlhc.oa.dao.RoleMapper;
import com.jlhc.oa.dao.RoleUserRelationMapper;
import com.jlhc.oa.dao.UserMapper;
import com.jlhc.oa.dto.function.FuncRoleRelation;
import com.jlhc.oa.dto.role.*;
import com.jlhc.oa.dto.role.example.RoleExample;
import com.jlhc.oa.dto.role.example.RoleUserRelationExample;
import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.service.FunctionService;
import com.jlhc.oa.service.RoleService;
import com.jlhc.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 16:54 2018/1/4 0004
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl implements RoleService{

    /**注入roleMapper*/
    @Autowired
    RoleMapper roleMapper;

    /**注入roleUserRelationMapper*/
    @Autowired
    RoleUserRelationMapper roleUserRelationMapper;

    /**注入roleGroupMapper*/
    @Autowired
    RoleGroupMapper roleGroupMapper;

    /**注入userMapper*/
    @Autowired
    UserMapper userMapper;

    /**注入functionService*/
    @Autowired
    FunctionService functionService;

    /**注入UserService*/
    @Autowired
    UserService userService;

    private RoleUserRelationExample roleUserRelationExample = new RoleUserRelationExample();

    private RoleExample roleExample = new RoleExample();



    /**
     * 查询所有角色集合
     *
     * @return
     */
    @Override
    public List<Role> queryAllRoles() {
        return new ArrayList<Role>();
    }

    /**
     * 创建角色同时指定角色所属分组
     *
     * @param role
     * @return
     */
    @Override
    public Integer createRole(Role role) {
        Integer resultNum = 0;
        //角色数据合理性校验
        Integer roleGroupId = role.getRoleGroupId();
        boolean hasRoleGroup = this.hasRoleGroup(roleGroupId);
        boolean isReasonedRole = this.isReasonedRole(role);
        if (hasRoleGroup && isReasonedRole){
            //条件符合执行插入操作
            role.setRoleCreatedtime(new Date());
            resultNum += roleMapper.insert(role);
        } else if (hasRoleGroup && !isReasonedRole){
            resultNum = -2;
        } else if (!hasRoleGroup && isReasonedRole){
            resultNum = -3;
        }
        return resultNum;
    }

    /**
     * 修改角色基本数据
     *
     * @param role
     * @return
     */
    @Override
    public Integer reworkBaseRole(Role role) {
        Integer resultNum = 0;
        Integer roleId = role.getRoleId();
        boolean hasRoleGroup = hasRoleGroup(role.getRoleGroupId());//组存在
        boolean isReasonedRole = isReasonedRole(role);//数据合理,同组下角色不重名
        if (hasRoleGroup && isReasonedRole){
            //条件符合执行修改角色数据操作
            resultNum += roleMapper.updateByPrimaryKey(role);
        } else if (hasRoleGroup && !isReasonedRole){
            resultNum = -2;
        } else if (!hasRoleGroup && isReasonedRole){
            resultNum = -3;
        }
        return resultNum;
    }

    /**
     * 根据用户查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> queryRolesByUserId(Integer userId) {
        if (null == userId){
            return null;
        }
        RoleUserRelationExample roleUserRelationExample = new RoleUserRelationExample();
        roleUserRelationExample.createCriteria()
                .andUserIdEqualTo(userId);
        List<RoleUserRelation> roleUserRelations = roleUserRelationMapper.selectByExample(roleUserRelationExample);
        List<Role> roles = new ArrayList<>();
        for (RoleUserRelation roleUserRelation:roleUserRelations) {
            Integer roleId = roleUserRelation.getRoleId();
            Role role = roleMapper.selectByPrimaryKey(roleId);
            roles.add(role);
        }
        return roles;
    }

    /**
     * 根据角色组查询角色
     *
     * @param roleGroupId
     * @return
     */
    @Override
    public List<Role> queryRolesByGroupId(Integer roleGroupId) {
        if (null == roleGroupId){
            return null;
        }
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria()
                .andRoleGroupIdEqualTo(roleGroupId);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return roles;
    }

    /**
     * 给指定用户添加角色
     *
     * @param userId
     * @param roleIds
     * @return -3 , 用户名或角色不存在, 0 ,表示添加的角色都已经在内,不需对数据库进行修改
     */
    @Override
    public Integer createRoleRelationsToUser(Integer userId, List<Integer> roleIds) {
        Integer resultNum = 0;
        //数据校验
        //用户数据校验
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            return -3;
        };
        //角色数据校验
        for (Integer roleId: roleIds) {
            Role role = roleMapper.selectByPrimaryKey(roleId);
            if (null == role){
                return -3;
            }
        }
        //根据用户查询原有的用户角色关系数据
        RoleUserRelationExample roleUserRelationExample = new RoleUserRelationExample();
        roleUserRelationExample.createCriteria().
                andUserIdEqualTo(userId);
        List<RoleUserRelation> roleUserRelations = roleUserRelationMapper.selectByExample(roleUserRelationExample);
        for (Integer roleId:roleIds) {
            if (null ==  roleId){
                return -3;
            }
            Boolean hasRole = false;
            for (RoleUserRelation roleUserRelation:roleUserRelations) {
                if (roleId == roleUserRelation.getRoleId()){
                   hasRole = true;
                }
            }
            if (false == hasRole){
                //添加操作
                RoleUserRelation roleUserRelation = new RoleUserRelation();
                roleUserRelation.setUserId(userId);
                roleUserRelation.setRoleId(roleId);
                resultNum += roleUserRelationMapper.insert(roleUserRelation);
            }
        }
        return resultNum;
    }

    /**
     * 给角色添加用户
     *
     * @param roleId
     * @param userIds
     * @return -3 , 用户名或角色不存在, 0 ,表示添加的用户关系都已经在内,不需对数据库进行修改
     */
    @Override
    public Integer createUserRelationsToRole(Integer roleId, List<Integer> userIds) {
        Integer resultNum = 0;
        //数据校验
        //角色存在性校验
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (null == role){
            return -3;
        }
        //用户存在性校验
        for (Integer userId : userIds ) {
            User user = userMapper.selectByPrimaryKey(userId);
            if (null == user){
                return -3;
            }
        }
        //根据角色查询原有的用户角色关系数据
        RoleUserRelationExample roleUserRelationExample = new RoleUserRelationExample();
        roleUserRelationExample.createCriteria().
                andRoleIdEqualTo(roleId);
        List<RoleUserRelation> roleUserRelations = roleUserRelationMapper.selectByExample(roleUserRelationExample);
        for (Integer userId : userIds) {
            if (null == userId){
                return -3;
            }
            Boolean hasUser = false;
            for (RoleUserRelation roleUserRelation : roleUserRelations) {
               if (userId == roleUserRelation.getUserId()){
                   hasUser = true;
               }
            }
            if (false == hasUser){
                //添加操作
                RoleUserRelation roleUserRelation = new RoleUserRelation();
                roleUserRelation.setUserId(userId);
                roleUserRelation.setRoleId(roleId);
                resultNum += roleUserRelationMapper.insert(roleUserRelation);
            }
        }
        return resultNum;
    }

    /**
     * 删除角色下的用户
     *
     * @param roleIdAndUserIds
     * @return
     */
    @Override
    public Integer dropRoleUserRelationOfRole(RoleIdAndUserIds roleIdAndUserIds) {
        //存在性校验
        Integer resultNum = 0;
        //数据校验
        Integer roleId = roleIdAndUserIds.getRoleId();
        List<Integer> userIds = roleIdAndUserIds.getUserIds();
        //条件
        roleUserRelationExample.clear();
        RoleUserRelationExample.Criteria criteria = roleUserRelationExample.createCriteria();

        //角色存在性校验
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (null == role){
            return -3;
        }
        criteria.andRoleIdEqualTo(roleId);
        //用户存在性校验
        for (Integer userId : userIds) {
            User user = userMapper.selectByPrimaryKey(userId);
            if (null == user){
                return -3;
            }
        }
        criteria.andUserIdIn(userIds);
        resultNum += roleUserRelationMapper.deleteByExample(roleUserRelationExample);
        return resultNum;
    }

    /**
     * 删除一个用户下的一个或者几个角色,这里是删除关系
     *
     * @param userIdAndRoleIds
     * @return
     */
    @Override
    public Integer dropRoleUserRelationWithOfUser(UserIdAndRoleIds userIdAndRoleIds) {
        //存在性校验
        Integer resultNum = 0;
        //数据校验
        Integer userId = userIdAndRoleIds.getUserId();
        List<Integer> roleIds = userIdAndRoleIds.getRoleIds();
        //条件
        roleUserRelationExample.clear();
        RoleUserRelationExample.Criteria criteria = roleUserRelationExample.createCriteria();

        //用户存在性校验
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            return -3;
        }
        criteria.andUserIdEqualTo(userId);
        //角色存在性校验
        for (Integer roleId : roleIds) {
            Role role = roleMapper.selectByPrimaryKey(roleId);
            if (null == role){
                return -3;
            }
        }
        criteria.andRoleIdIn(roleIds);
        resultNum += roleUserRelationMapper.deleteByExample(roleUserRelationExample);
        return resultNum;
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public Integer dropRole(Integer roleId) {
        //数据非空校验
        Integer resultNum = 0;
        if (null == roleId){
            return resultNum;
        }
        //数据存在性校验
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (null == role){
            //没有这个角色直接返回
            return -3;
        }
        //判断是否还有角色用户的关系
        List<User> users = userService.queryUsersByRoleId(roleId);
        if (0 < users.size()){
            //有用户角色关系直接删除
            roleUserRelationExample.clear();
            roleUserRelationExample.createCriteria()
                    .andRoleIdEqualTo(roleId);
            resultNum += roleUserRelationMapper.deleteByExample(roleUserRelationExample);
        }
        //删除RoleFunc关系
        List<FuncRoleRelation> relations = functionService.queryFuncRoleRelationsOfRole(roleId);
        if (0 < relations.size()) {
            //有角色和功能的关系也直接删除
            resultNum += functionService.dropRoleFunctionRelationsOfRole(roleId);
        }
        //删除角色基本信息
        resultNum += roleMapper.deleteByPrimaryKey(roleId);
        return resultNum;
    }

    @Override
    public Role queryRoleInfoById(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    /**
     * 开机自检校验默认角色数据是否正常
     *
     * @return
     */
    @Override
    public Boolean hasDefRoleData() {
        Role role = roleMapper.selectByPrimaryKey(2);
        if (null != role&&role.getRoleId() == 2){
            return true;
        }
        return false;
    }


    /**
     *判定是否有输入的角色组
     *
     * @param roleGroupId
     * @return true,有;flase,无;
     */
    private boolean hasRoleGroup(Integer roleGroupId){
        RoleGroup roleGroup = roleGroupMapper.selectByPrimaryKey(roleGroupId);
        if (null == roleGroup){
            return false;
        }
        return true;
    }

    /**
     * 判定角色数据是否合理
     *
     * @param role
     * @return true,合理,false,不合理,同一个组下有相同的角色名
     */
    private boolean isReasonedRole(Role role){
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria()
            .andRoleNameEqualTo(role.getRoleName())
            .andRoleGroupIdEqualTo(role.getRoleGroupId());
        //根据角色名称和角色组查询角色
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if (0 < roles.size()){
            return false;
        }
        return true;
    }
}
