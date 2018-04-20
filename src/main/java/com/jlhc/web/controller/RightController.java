package com.jlhc.web.controller;

import com.jlhc.common.utils.ResultUtil;
import com.jlhc.common.dto.Msg;
import com.jlhc.oa.dto.function.FuncRightRelation;
import com.jlhc.oa.dto.function.Function;
import com.jlhc.oa.dto.function.Module;
import com.jlhc.oa.dto.function.Right;
import com.jlhc.oa.dto.right.RightAndFuncId;
import com.jlhc.oa.service.FunctionService;
import com.jlhc.oa.service.ModuleService;
import com.jlhc.oa.service.RightService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/right")
@Api(description = "该接口提供给开发人员用于细分权限管理")
public class RightController extends BaseController{

    @Autowired
    ModuleService moduleService;

    @Autowired
    FunctionService functionService;

    @Autowired
    RightService rightService;

    @RequestMapping(value = "postModule" , method = RequestMethod.POST)
    @RequiresPermissions("right:postModule")
    public Msg postModule(@RequestBody @Valid Module module){
        try{
            Integer resultNum = moduleService.createModule(module);
            if (resultNum == 0){
                return ResultUtil.operationFailed("出现重名,插入失败");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 给模块添加一个功能,超级管理员用
     * @param function
     * @return
     */
    @RequestMapping(value = "postFunction2Module", method = RequestMethod.POST)
    @RequiresPermissions("right:postFunction2Module")
    public Msg postFunction2Module(@RequestBody @Valid Function function){
        try{
            Integer resultNum = functionService.createFunction(function);
            if (resultNum == 0){
                return ResultUtil.operationFailed("插入数据数据为" + resultNum + ",同一模块有重复数据!");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    };

    /**
     * 写个接口,把它赋予某个功能
     *
     * @param //rightAndFuncId
     * @return
     */
    @RequestMapping(value = "postRight2Function" , method = RequestMethod.POST)
    @RequiresPermissions("right:postRight2Function")
    public Msg postRight2Function(@RequestBody @Valid RightAndFuncId rightAndFuncId, BindingResult bindingResult){
        try{
            Integer resultNum = rightService.createRight2Function(rightAndFuncId);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("数据重复插入");
            }else if (-3 == resultNum){
                return ResultUtil.operationFailed("数据不存在,不能建立关系");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据权限名称模糊查询权限信息
     *
     * @param rightName
     * @return
     */
    @RequestMapping(value = "getRightsBySlurName" , method = RequestMethod.GET)
    @RequiresPermissions("right:getRightsBySlurName")
    public Msg getRightsBySlurName(@RequestParam @NotNull @Length(max = 64) String rightName){
        try{
            String slurName = "%"+rightName+"%";
            List<Right> rights = rightService.queryRightBySlurName(slurName);
            if (rights.size() > 0){
                return ResultUtil.selectSuccess(rights);
            }
            return ResultUtil.operationFailed("未查询到数据");
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据rightdata模糊查询接口权限的详细信息
     *
     * @param rightData
     * @return
     */
    @RequestMapping(value = "getRightsBySlurRightData" , method = RequestMethod.GET)
    @RequiresPermissions("right:getRightsBySlurRightData")
    public Msg getRightsBySlurRightData(@RequestParam @NotNull @Length(max = 64) String rightData){
        try{
            String slurData = "%"+rightData+"%";
            List<Right> rights= rightService.queryRightsBySlurData(slurData);
            if (rights.size() > 0){
                return ResultUtil.selectSuccess(rights);
            }
            return ResultUtil.operationFailed("未查询到数据");
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据功能Id查询权限
     *
     * @param funcId
     * @return
     */
    @RequestMapping(value = "getRightsByFuncId/{funcId}" , method = RequestMethod.GET)
    @RequiresPermissions("right:getRightsByFuncId")
    public Msg getRightsByFuncId(@PathVariable @Max(999999999) @NotNull Integer funcId){
        try{
            List<Right> rights = rightService.queryRightsByFuncId(funcId);
            if (rights.size() > 0){
                return ResultUtil.selectSuccess(rights);
            }
            return ResultUtil.selectSuccess(rights);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据功能名模糊查询功能集合
     *
     * @param funcName
     * @return
     */
    @RequestMapping(value = "getFunctionsBySulrFuncName" , method = RequestMethod.GET)
    @RequiresPermissions("right:getFunctionsBySulrFuncName")
    public Msg getFunctionsBySulrFuncName(@RequestParam @NotNull @Length(max = 64) String funcName){
        try{
            String sulrName = "%"+funcName+"%";
            List<Function> functions = functionService.queryFunctionBySulrFuncName(sulrName);
            if (functions.size()>0){
                return ResultUtil.selectSuccess(functions);
            }
            return ResultUtil.operationFailed("未查询到数据");
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 删除某一个接口权限,同时删除功能与接口的关系,用于书写的接口被删除的时候,不常用
     *
     * @param rightId
     * @return
     */
    @DeleteMapping(value = "deleteRightAndFuncRightRelation/{rightId}")
    @RequiresPermissions("right:deleteRightAndFuncRightRelation")
    public Msg deleteRightAndFuncRightRelation(@PathVariable @Max(999999999) @NotNull Integer rightId){
        try{
            Integer resultNum = 0;
            resultNum += rightService.dropRightAndFuncRightRelation(rightId);
            if (0 == resultNum){
                return ResultUtil.operationFailed("数据已经没有了,不用再删了");
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 创建关系
     *
     * @param funcRightRelation
     * @return
     */
    @PostMapping(value = "postFuncRightRelation")
    @RequiresPermissions("right:postFuncRightRelation")
    public Msg postFuncRightRelation(@RequestBody @Valid FuncRightRelation funcRightRelation){
        try{
            Integer resultNum = 0;
            Integer funcId = funcRightRelation.getFuncId();
            Integer rightId = funcRightRelation.getRightId();
            resultNum += rightService.createFuncRightRelation(rightId,funcId);
            if (0 == resultNum){
                return ResultUtil.operationFailed("建立关系失败");
            }else if (-2 == resultNum){
                return ResultUtil.operationFailed("数据重复");
            }else if(-3 == resultNum){
                return ResultUtil.operationFailed("数据不存在,无法建立关系");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }
}
