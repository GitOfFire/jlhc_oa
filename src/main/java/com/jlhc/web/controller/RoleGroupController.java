package com.jlhc.web.controller;

import com.jlhc.common.utils.ResultUtil;
import com.jlhc.common.dto.Msg;
import com.jlhc.oa.dto.role.RoleGroup;
import com.jlhc.oa.dto.role.RoleGroupWithoutState;
import com.jlhc.oa.service.RoleGroupService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 组的修改
 *
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 10:38 2018/1/5 0005
 */
@RestController
@RequestMapping(value = "rolegroup")
public class RoleGroupController extends BaseController{
    /**RoleService*/
    @Autowired
    RoleGroupService roleGroupService;

    /**
     * 组创建,同一个组织机构不允许创建同名组
     *
     * @param roleGroupWithoutState 组信息
     * @return
     */
    //value:right唯一标识,notes:方法描述,nickname:方法名
    @ApiOperation(value = "23761sdyysd",notes = "用户组:添加",nickname = "用户组添加")
    @RequiresPermissions("rolegroup:postRoleGroup")
    @RequestMapping(value = "postRoleGroup",method = RequestMethod.POST)
    public Msg postRoleGroup(@RequestBody @Valid RoleGroupWithoutState roleGroupWithoutState, BindingResult bindingResult){
        //可以研究该判断方法的重构,采用AOP处理
        /*if (bindingResult.hasErrors()){
            Map<String, String> vaildErrorMap = super.getVaildErrorMap(bindingResult);
            //return ResultUtil.vaildError(errorDefaultMessages);
            return ResultUtil.vaildError(vaildErrorMap);
        }*/
        try {
            Integer resultNum = roleGroupService.createRoleGroupWithoutTheSameName(roleGroupWithoutState);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("组名称相同");
            } else if (0 == resultNum){
                return ResultUtil.operationFailed();
            } else if (-3 == resultNum){
                return ResultUtil.operationFailed("组织机构不存在");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch (Exception e){
            logger.error("创建组动作发生错误"+e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 一般根据组织机构查询角色组,不能查询用户所属组织机构下的角色组
     *
     * @return 得到的角色组集合
     */
    @ApiOperation(value = "78789ssdfij11",notes = "用户组:查询",nickname = "用户组查询")
    @RequestMapping(value = "getRoleGroups",method = RequestMethod.GET)
    @RequiresPermissions("rolegroup:getRoleGroups")
    public Msg getRoleGroups(HttpSession httpSession){
        try {
            List<RoleGroup> roleGroups = roleGroupService.findRoleGroups(httpSession);
            if (0 == roleGroups.size()|| null == roleGroups){
                ResultUtil.selectFailed();
            }
            //查询用户对应的角色组,用于页面展示
            return ResultUtil.selectSuccess(roleGroups);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 该接口主要目标修改角色组名称
     *
     * @return 修改成功,修改成功反馈,修改失败,修改失败原因,
     */
    @ApiOperation(value = "798787sdfsf",notes = "角色组:修改",nickname = "角色组修改")
    @RequestMapping(value = "putRoleGroup",method = RequestMethod.PUT)
    @RequiresPermissions("rolegroup:putRoleGroup")
    public Msg putRoleGroup(@RequestBody @Valid RoleGroup roleGroup){
        /*if(bindingResult.hasErrors()){
            Map<String, String> vaildErrorMap = super.getVaildErrorMap(bindingResult);
            return ResultUtil.vaildError(vaildErrorMap);
        }*/
        try {
            Integer resultNum = roleGroupService.updateRoleGroupWithoutTheSameName(roleGroup);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("组名称相同");
            } else if (0 == resultNum){
                return ResultUtil.operationFailed();
            } else if (-3 == resultNum){
                return ResultUtil.operationFailed("组织机构或者角色组不存在");
            } else if (-4 == resultNum){
                return ResultUtil.operationFailed("默认分组不允许修改");
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 用户组删除
     *
     * @param roleGroupId
     * @return
     */
    @RequestMapping(value = "deleteRoleGroup/{roleGroupId}",method = RequestMethod.DELETE)
    @RequiresPermissions("rolegroup:deleteRoleGroup")
    public Msg deleteRoleGroup(@PathVariable @Max(999999999)@Min(1) Integer roleGroupId){
        try {
            Integer resultNum = roleGroupService.dropRoleGroupById(roleGroupId);
            if (-4 == resultNum){
                //说明是默认用户组,只能随之组织机构的销毁而销毁
                return ResultUtil.operationFailed("默认分组不允许删除");
            }
            if (0 == resultNum){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

}
