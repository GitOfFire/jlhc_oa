package com.jlhc.web.controller;

import com.jlhc.common.dto.Msg;
import com.jlhc.common.utils.ResultUtil;
import com.jlhc.oa.dto.role.Role;
import com.jlhc.oa.dto.role.RoleIdAndUserIds;
import com.jlhc.oa.dto.role.UserIdAndRoleIds;
import com.jlhc.oa.service.RoleService;
import org.apache.commons.collections.map.UnmodifiableMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色模块接口
 *
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 9:16 2018/1/22 0022
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController{

    public static final Map<Integer,String> RESULTNUM_MSG_MAP;

    static{
        Map<Integer, String> resultNumMsgMap = new HashMap<Integer, String>();
        resultNumMsgMap.put(-2, "出现重复数据");
        resultNumMsgMap.put(-3, "实体数据不存在");
        resultNumMsgMap.put(0,"未修改任何数据");
        RESULTNUM_MSG_MAP = UnmodifiableMap.decorate(resultNumMsgMap);//这是一个不可修改的Map
    }

    /**注入roleService*/
    @Autowired
    private RoleService roleService;

    /**
     *角色添加
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "postRole",method = RequestMethod.POST)
    @RequiresPermissions("role:postRole")
    public Msg postRole(@RequestBody @Valid Role role, BindingResult bindingResult){
        try {
            Integer resultNum = roleService.createRole(role);
            //根据不同的resultNum进行响应处理
            if(0 <= resultNum){
                return ResultUtil.addSuccess(resultNum);
            }
            return ResultUtil.operationFailed(RESULTNUM_MSG_MAP.get(resultNum));
        }catch (Exception e){
            logger.error("添加角色报错",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 修改角色基本信息
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "putBaseRole",method = RequestMethod.PUT)
    @RequiresPermissions("role:putBaseRole")
    public Msg putBaseRole(@RequestBody @Valid Role role,BindingResult bindingResult){
        try {
            Integer resultNum = roleService.reworkBaseRole(role);
            //根据不同的resultNum进行响应处理
            if(0 < resultNum){
                return ResultUtil.updateSuccess(resultNum);
            }
            return ResultUtil.operationFailed(RESULTNUM_MSG_MAP.get(resultNum));
        }catch (Exception e){
            logger.error("修改用户基本信息",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据用户ID查询所属角色
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "getRolesByUserId/{userId}",method = RequestMethod.GET)
    @RequiresPermissions("role:getRolesByUserId")
    public Msg getRolesByUserId(@PathVariable @Max(999999999) Integer userId){
        try {
            List<Role> rolesByUserId = roleService.queryRolesByUserId(userId);
            if (null == rolesByUserId || 0 == rolesByUserId.size()){
                return ResultUtil.operationFailed("没有查询到对应的角色");
            }
            return ResultUtil.selectSuccess(rolesByUserId);
        }catch (Exception e){
            logger.error("查询用户ID所属角色",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据角色组查询角色
     *
     * @param roleGroupId
     * @return
     */
    @RequestMapping(value = "getRolesByGroup/{roleGroupId}",method = RequestMethod.GET)
    @RequiresPermissions("role:getRolesByGroup")
    public Msg getRolesByGroup(@PathVariable @Max(999999999) Integer roleGroupId){
        try {
            List<Role> rolesByGroupId = roleService.queryRolesByGroupId(roleGroupId);
            if (null == rolesByGroupId || 0 == rolesByGroupId.size()){
                return ResultUtil.operationFailed("没有查询到对应的角色");
            }
            return ResultUtil.selectSuccess(rolesByGroupId);
        }catch (Exception e){
            logger.error("根据角色组查询角色",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 给用户增加角色
     *
     * @param userIdAndRoleIds --其中用户的角色集合为该用户需要添加的角色,已有则不变,未有则添加
     * @return
     */
    @RequestMapping(value = "postRolesToUser",method = RequestMethod.POST)
    @RequiresPermissions("role:postRolesToUser")
    public Msg postRolesToUser(@RequestBody @Valid UserIdAndRoleIds userIdAndRoleIds, BindingResult bindingResult){//对于校验是一个不同的考验,相同的校验是否适用与不同的数据输入需求,这里要引入的是对于用户前端后端两种对话不同的校验方案
        try {

            Integer resultNum = roleService.createRoleRelationsToUser(userIdAndRoleIds.getUserId(),userIdAndRoleIds.getRoleIds());//两个参数,一个是用户Id,一个是角色
            //根据不同的resultNum进行响应处理
            if(0 < resultNum){
                return ResultUtil.updateSuccess(resultNum);
            }
            return ResultUtil.operationFailed(RESULTNUM_MSG_MAP.get(resultNum));
        }catch (Exception e){
            logger.error("给用户增加角色",e);
            return ResultUtil.error(e);
        }
}

    /**
     * 给角色添加用户
     *
     * @param roleIdAndUserIds --其中userIds为需要添加的用户
     * @return
     */
    @RequestMapping(value = "postUsersToRole",method = RequestMethod.POST)
    @RequiresPermissions("role:postUsersToRole")
    public Msg postUsersToRole(@RequestBody @Valid RoleIdAndUserIds roleIdAndUserIds,BindingResult bindingResult){
        try {
            Integer roleId = roleIdAndUserIds.getRoleId();
            List<Integer> userIds = roleIdAndUserIds.getUserIds();
            Integer resultNum = roleService.createUserRelationsToRole(roleId,userIds);
            if(0 < resultNum){
                return ResultUtil.addSuccess(resultNum);
            }
            return ResultUtil.operationFailed(RESULTNUM_MSG_MAP.get(resultNum));
        }catch (Exception e){
            logger.error("给角色添加用户",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 删除某一角色下的一个或者几个用户
     *
     * @param roleIdAndUserIds
     * @return
     */
    @DeleteMapping(value = "deleteRoleUserRelationsOfRole")
    @RequiresPermissions("role:deleteRoleUserRelationsOfRole")
    public Msg deleteRoleUserRelationsOfRole(@RequestBody @Valid RoleIdAndUserIds roleIdAndUserIds,BindingResult bindingResult){
        try{
            Integer resultNum = roleService.dropRoleUserRelationOfRole(roleIdAndUserIds);
            if (-3 == resultNum){
                return ResultUtil.operationFailed("所要删除的关系不存在实体,无法删除,检查数据库是否存在垃圾数据");
            }else if (0 == resultNum){
                return ResultUtil.operationFailed("未删除任何数据");
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 删除一个用户下的某些角色关系
     *
     * @param userIdAndRoleIds
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "deleteRoleUserRelationsOfUser")
    @RequiresPermissions("role:deleteRoleUserRelationsOfUser")
    public Msg deleteRoleUserRelationsOfUser(@RequestBody @Valid UserIdAndRoleIds userIdAndRoleIds,BindingResult bindingResult){
        try{
            Integer resultNum = 0;
            resultNum += roleService.dropRoleUserRelationWithOfUser(userIdAndRoleIds);
            if (resultNum == -3){
                return ResultUtil.operationFailed("所要删除的关系不存在实体,无法删除,检查数据库是否存在垃圾数据");
            }else if (0 == resultNum){
                return ResultUtil.operationFailed("未删除任何数据");
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 删除角色,主要处理两个问题,断开该角色与功能的关系
     * 删除该角色基本信息(物理删除)
     *
     * @param roleId
     * @return
     */
    @DeleteMapping(value = "deleteRole/{roleId}")
    @RequiresPermissions("role:deleteRole")
    public Msg deleteRole(@PathVariable @NotNull @Max(999999999) Integer roleId){
        try{
            Integer resultNum = roleService.dropRole(roleId);
            //-3为有垃圾数据,数据不存在
            if ( -3 == resultNum){
                return ResultUtil.operationFailed("数据不存在,无须执行删除");
            } else if ( 0 == resultNum){
                return ResultUtil.operationFailed();
            } else if ( -1 == resultNum){
                return ResultUtil.operationFailed("还有扮演此角色的用户,不能删除");
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 查询角色基本信息
     *
     * @param roleId
     * @return
     */
    @GetMapping(value = "getRoleInfoById/{roleId}")
    @RequiresPermissions("role:getRoleInfoById")
    public Msg getRoleInfoById(@PathVariable  @NotNull @Max(999999999) Integer roleId){
        try{
            Role role = roleService.queryRoleInfoById(roleId);
            if (null == role){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(role);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }
}
