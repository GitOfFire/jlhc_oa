package com.jlhc.web.controller;

import com.jlhc.common.dto.Msg;
import com.jlhc.common.utils.ResultUtil;
import com.jlhc.oa.dto.function.FuncIdWithSelected;
import com.jlhc.oa.dto.function.Function;
import com.jlhc.oa.dto.function.RoleIdAndFuncIdWithSelected;
import com.jlhc.oa.dto.role.RoleIdAndFuncIds;
import com.jlhc.oa.dto.role.RoleIdAndModuleId;
import com.jlhc.oa.service.FunctionService;
import org.apache.commons.collections.map.UnmodifiableMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * 功能操作
 *
 * @author renzhong
 * @version 1.0 , 2018-1-26 14:53
 */
@RestController
@RequestMapping("/function")
public class FunctionController extends BaseController{
    /**功能service*/
    @Autowired
    private FunctionService functionService;

    public static final Map<Integer,String> RESULTNUM_MSG_MAP;

    static{
        Map<Integer, String> resultNumMsgMap = new HashMap<Integer, String>();
        resultNumMsgMap.put(-2, "出现重复数据");
        resultNumMsgMap.put(-3, "角色,模块或功能数据不存在");
        resultNumMsgMap.put(-4, "处理的功能属于两个模块,本接口不予处理");
        resultNumMsgMap.put(0,"未修改任何数据");
        RESULTNUM_MSG_MAP = UnmodifiableMap.decorate(resultNumMsgMap);//这是一个不可修改的Map
    }

    /**
     * 根据角色查询对应的function
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "getFunctionsByRoleId/{roleId}",method = RequestMethod.GET)
    @RequiresPermissions("function:getFunctionsByRoleId")
    public Msg getFunctionsByRoleId(@PathVariable @Max(999999999) @NotNull Integer roleId){
        try{
            //String为模块名
            Map<String,HashSet<Function>> map = functionService.queryFunctionByRoleId(roleId);
            if (null == map || 0 == map.size()){
                return ResultUtil.operationFailed("未查询到数据");
            }
            return  ResultUtil.selectSuccess(map);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     *
     * 修改角色对应的功能数据
     *
     * @param roleIdAndFuncIds 入参只包括单一模块的信息
     * @return
     */
    @RequestMapping(value = "putFunctionOfRole",method = RequestMethod.PUT)
    @RequiresPermissions("function:putFunctionOfRole")
    public Msg putFunctionOfRole(@RequestBody @Valid RoleIdAndFuncIds roleIdAndFuncIds, BindingResult bindingResult){
        try{
            //根据不同的返回值给前台返回相应的数据
            Integer resutlNum = functionService.reworkRoleFuncRelations(roleIdAndFuncIds);
            if (0 < resutlNum){
                return ResultUtil.operationSuccess(resutlNum.toString());
            }
            return ResultUtil.operationFailed(RESULTNUM_MSG_MAP.get(resutlNum));
        }catch (Exception e){
            logger.error("修改角色所对应的功能出错啦",e);
            return ResultUtil.error(e);
        }
    }

    //一旦出现空选,就上述修改就不管事了,需要一个删除

    /**
     * 删除逻辑,一旦传入的修改对象为空,调用此接口
     *
     * @param roleIdAndModuleId
     * @param bindingResult
     * @return
     */
    @DeleteMapping("/deleteAllFuncRoleOfModule")
    @RequiresPermissions("function:deleteAllFuncRoleOfModule")
    public Msg deleteAllFuncRoleOfModule(@RequestBody RoleIdAndModuleId roleIdAndModuleId, BindingResult bindingResult){
        logger.info("roleIdAndModuleId"+roleIdAndModuleId);
        try{
            Integer resultNum = functionService.dropRoleFunctionAllRelationOfModule(roleIdAndModuleId);
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            logger.error("删除某一角色的某一模块所有功能",e);
            return ResultUtil.error(e);
        }
    }

    //重新设计接口,修改接口,和删除接口都废弃

    /**
     * 超牛增删改一应俱全接口
     *
     * @param roleIdAndFuncIdWithSelected
     * @return
     */
    @PostMapping(value = "/postFunctionsOfRole")
    @RequiresPermissions("function:postFunctionsOfRole")
    public Msg postFunctionsOfRole(@RequestBody @Valid RoleIdAndFuncIdWithSelected roleIdAndFuncIdWithSelected,BindingResult bindingResult){
        try{
            Integer roleId = roleIdAndFuncIdWithSelected.getRoleId();
            List<FuncIdWithSelected> funcIdsWithSelected = roleIdAndFuncIdWithSelected.getFuncIdsWithSelected();
            //去重
            List<FuncIdWithSelected> funcIdsWithSelectedWithoutTheSameId = new ArrayList<>();
            for (FuncIdWithSelected f :funcIdsWithSelected ) {
                //如果集合里面没有相同的元素才往里存
                if(!funcIdsWithSelectedWithoutTheSameId.contains(f)){
                    funcIdsWithSelectedWithoutTheSameId.add(f);
                }else{
                    return ResultUtil.operationFailed("你怎么能一个功能改好多遍,我哪知道哪个是最终版");
                }
            }
            //挑选出要添加的
            List<Integer> createdFuncIds = new ArrayList<>();
            List<Integer> dropedFuncIds=  new ArrayList<>();
            for (FuncIdWithSelected f :funcIdsWithSelectedWithoutTheSameId ) {
                if (f.isSelected()){
                    createdFuncIds.add(f.getFuncId());
                }else{
                    dropedFuncIds.add(f.getFuncId());
                }
            }
            //执行添加
            Integer resultNum = functionService.createRoleFuncRelations(roleId,createdFuncIds);
            if (-3 == resultNum){
                return ResultUtil.operationFailed("角色/功能ID对应的实体不存在,不能添加它们的关系!!");
            }
            //执行删除
            resultNum += functionService.dropRoleFuncRelations(roleId,dropedFuncIds);
            return ResultUtil.operationSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }
}
