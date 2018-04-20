package com.jlhc.web.controller;

import com.jlhc.common.exception.NullDataForInsertException;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.common.utils.ExcelReader;
import com.jlhc.common.utils.RandomStringUtil;
import com.jlhc.common.utils.ResultUtil;
import com.jlhc.common.dto.Msg;
import com.jlhc.oa.dto.user.User;
import com.jlhc.sell.dto.*;
import com.jlhc.sell.service.TaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 任务操作
 */
@RestController
@RequestMapping("/task")
public class SellTaskController extends BaseController {
    @Autowired
    private TaskService taskService;

    /**
     * 通过Id查询单个任务详情
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "getTaskById/{taskId}")
    @RequiresPermissions(value = "task:getTaskById")
    public Msg getTaskById(@PathVariable @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码") String taskId){
        try{
            TaskFullInfo task = taskService.queryTaskById(taskId);
            if (null == task){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(task);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 自己负责的任务中添加单条任务
     *
     * @param taskForCreate
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "postTask")
    @RequiresPermissions(value = "task:postTask")
    public Msg postTask(@RequestBody @Valid TaskForCreate taskForCreate, BindingResult bindingResult, HttpSession httpSession){
        try{
            User user = (User)httpSession.getAttribute("user");
            Task task = new Task();
            task.setComId(taskForCreate.getComId());
            //创建任务时,指定客户经理
            task.setHoldUserId(taskForCreate.getHoldUserId());
            task.setTaskContent(taskForCreate.getTaskContent());
            task.setTaskCreatedTime(new Date());
            task.setTaskCreatedUser(user.getUserId());
            task.setTaskState(0);//任务状态默认为0
            Integer resultNum = taskService.createTask(task);
            return ResultUtil.addSuccess(resultNum);
        }catch (NullEntityInDatabaseException e){
            return errorResultOperation(e);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 修改任务信息
     *
     * @param task
     * @param bindingResult
     * @return
     */
    @PutMapping(value = "putTask")
    @RequiresPermissions(value = "task:putTask")
    public Msg putTask(@RequestBody @Valid TaskForPut task,BindingResult bindingResult,HttpSession httpSession){
        try{

            Integer resultNum = taskService.reworkTask(task,httpSession);
            if (-31 == resultNum){
                return ResultUtil.operationFailed("任务所属的客户(公司)不存在!!");
            }
            if (-32 == resultNum){
                return ResultUtil.operationFailed("任务所属的客户经理不存在!!");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 删除任务,将任务的状态改为废弃
     *
     * @param taskId
     * @return
     */
    @DeleteMapping(value = "deleteTask/{taskId}")
    @RequiresPermissions(value = "task:deleteTask")
    public Msg deleteTask(@PathVariable @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码") String taskId,
                          HttpSession session){
        try{
            User user =(User)session.getAttribute("user");
            Integer resultNum = taskService.dropTask(taskId,user);
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 根据联系人姓名或者电话号码查询任务
     *
     * @param nameOrMobile
     * @return
     */
    @GetMapping(value = "getTasksByNameOrMobile")
    @RequiresPermissions(value = "task:getTasksByNameOrMobile")
    public Msg getTasksByNameOrMobile(@RequestParam @Length(min = 0,max = 255) String nameOrMobile){
        try{
            List<Task> tasks = taskService.queryTasksByNameOrMobile(nameOrMobile);
            if (null == tasks||0 == tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 根据公司名称查询任务
     *
     * @param comName
     * @return
     */
    @GetMapping("getTasksByComName")
    @RequiresPermissions(value = "task:getTasksByComName")
    public Msg getTasksByComName(@RequestParam @Length(min = 0,max = 255) String comName){
        try{
            List<Task> tasks = taskService.queryTasksByComName(comName);
            if (null == tasks||0 == tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 根据创建人查询任务
     *
     * @param createUserId
     * @return
     */
    @GetMapping("getTasksByCreateUserId/{createUserId}")
    @RequiresPermissions(value = "task:getTasksByCreateUserId")
    public Msg getTasksByCreateUserId(@PathVariable @Max(999999999) @NotNull  Integer createUserId){
        try{
            List<Task> tasks = taskService.queryTasksByCreateUserId(createUserId);
            if (null == tasks||0 == tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 根据当前负责人查询任务
     *
     * @param holdUserId
     * @return
     */
    @GetMapping("getTasksByHoldUserId/{holdUserId}")
    @RequiresPermissions("task:getTasksByHoldUserId")
    public Msg getTasksByHoldUserId(@PathVariable @Max(999999999) @NotNull Integer holdUserId){
        try{
            List<Task> tasks = taskService.queryTasksByHoldUserId(holdUserId);
            if (null == tasks||0 == tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 任务导入,上传文件,并且读取,将文件读取的结果存入数据库
     *
     * @param file
     * @return
     */
    @PostMapping("uploadTasks")
    @RequiresPermissions("task:uploadTasks")
    public Msg uploadTasks(@RequestParam(value = "file", required=false)@NotNull MultipartFile file,RedirectAttributes redirectAttributes, HttpSession httpSession){
        User user = null;
        if (file.isEmpty()) {
            return ResultUtil.operationFailed("Please select a file to upload");
        }
        String filePath = file.getOriginalFilename();
        File asset = new File("asset");
        if (!asset.exists()){
            if (!asset.mkdirs()){
                return ResultUtil.operationFailed("系统问题,上传文件的目标文件夹创建失败!!");
            }
        }
        filePath = "asset/" + filePath; // 这是文件的保存路径，如果不设置就会保存到项目的根目录
        BufferedOutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        try{
            user = (User) httpSession.getAttribute("user");
            fileOutputStream = new FileOutputStream(filePath);
            outputStream = new BufferedOutputStream(fileOutputStream);
            outputStream.write(file.getBytes());
            outputStream.flush();
            fileInputStream = new FileInputStream(filePath);
            inputStream = new BufferedInputStream(fileInputStream);
            ExcelReader excelReader = new ExcelReader();
            String[] excelTitle = excelReader.readExcelTitle(inputStream);
            fileInputStream = new FileInputStream(filePath);
            inputStream = new BufferedInputStream(fileInputStream);
            Map<Integer, String> excelContent = excelReader.readExcelContent(inputStream);
            for (String s : excelTitle) {
                System.out.println(s);
            }
            List<Task> tasks = new ArrayList<>();
            List<Flow> flows = new ArrayList<>();
            for (Map.Entry entry : excelContent.entrySet()) {
                String taskContent = (String)entry.getValue();
                Task task = new Task();
                Flow flow = new Flow();
                Date date = new Date();
                flow.setFlowId(RandomStringUtil.getRandomCode(32, 7));
                task.setTaskContent(taskContent);
                String randomCode = RandomStringUtil.getRandomCode(32, 7);
                task.setTaskId(randomCode);
                task.setTaskCreatedTime(date);
                task.setTaskCreatedUser(user.getUserId());
                flow.setFlowTime(date);
                flow.setFlowUserId(user.getUserId());
                flow.setFlowDescription(user.getUserTruename()+"创建任务");
                flow.setFlowType(1);//发生了人变更
                flow.setTaskId(randomCode);
                flows.add(flow);
                tasks.add(task);
            }
            Integer resultNum = taskService.createTasks(tasks,flows);
            return ResultUtil.operationSuccess("新建了"+resultNum+"条数据!!");
        } catch (NullDataForInsertException e) {
            return errorResultOperation(e);
        } catch(Exception e){
            return errorResultOperation(e);
        } finally{
            if (outputStream != null) try { outputStream.close(); } catch (IOException ignore) {
                ignore.printStackTrace();}
            if (inputStream != null) try { inputStream.close(); } catch (IOException ignore) {
                ignore.printStackTrace();}
            File updataFile = new File(filePath);
            if (updataFile.exists()){
                updataFile.delete();
            }
        }
    }
    /**
     * 根据流程的执行人查询任务记录
     *
     * @param flowUserId
     * @return
     */
    @GetMapping("getTasksByFlowUserId/{flowUserId}")
    @RequiresPermissions("task:getTasksByFlowUserId")
    public Msg getTasksByFlowUserId(@PathVariable @Max(999999999) @NotNull Integer flowUserId){
        try{
            List<Task> tasks = taskService.queryTasksByFlowUserId(flowUserId);
            if (null == tasks||0 == tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 将任务的负责人变换
     *
     * @param tasks
     * @return
     */
    @PutMapping("putTaskToChangeHoldUserId")
    @RequiresPermissions("task:putTaskToChangeHoldUserId")
    public Msg putTaskToChangeHoldUserId(@RequestBody @Size(min = 1) List<TaskForUpdateHoldUser> tasks, BindingResult bindingResult, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        try{
            Integer resultNum = taskService.reworkTaskToChangeHoldUserId(tasks,user);
            if (0 == resultNum){
                return ResultUtil.operationFailed("仅仅修改了"+resultNum/2+"条任务数据" );
            }
            return ResultUtil.updateSuccess("修改了"+resultNum/2+"条任务数据");
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 共享某一个任务给某一个用户
     *
     * @param userId
     * @param taskId
     * @return
     */
    @PostMapping("postRelationTaskToUser")
    @RequiresPermissions("task:postRelationTaskToUser")
    public Msg postUserToTask(@RequestParam @NotNull @Max(999999999)Integer userId,
                              @RequestParam @NotNull @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")String taskId,
                              HttpSession session){
        try{
            User user = (User)session.getAttribute("user");
            Integer operUserId = user.getUserId();
            Integer resultNum = taskService.createRelationTaskToUser(userId,taskId,operUserId);
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 删除一个共享关系
     *
     * @param taskId
     * @param userId
     * @param session
     * @return
     */
    @DeleteMapping("deleteTaskUserRelation")
    @RequiresPermissions("task:deleteTaskUserRelation")
    public Msg deleteTaskUserRelation(@RequestParam @NotNull @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")String taskId,
                                @RequestParam @NotNull @Max(999999999)Integer userId,
                                HttpSession session){
        try{
            User user =(User) session.getAttribute("user");
            Integer operUserId = user.getUserId();
            Integer resultNum = taskService.dropTaskUserRelation(taskId,userId,operUserId);
            if (0 >= resultNum){
                return ResultUtil.operationFailed("未删除任何数据");
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 获取当前登录人的所有共享任务
     *
     * @param session
     * @return
     */
    @GetMapping("getTasksByUserIdInTaskUserRelation")
    @RequiresPermissions("task:getTasksByUserIdInTaskUserRelation")
    public Msg getTasksByUserIdInTaskUserRelation(HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            Integer userId = user.getUserId();
            List<Task> tasks = taskService.queryTasksByUserIdInTaskUserRelation(userId);
            if (null == tasks || 0 >= tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 查询任务公海中待领取的任务
     *
     * @return
     */
    @GetMapping("getTasksInTaskSea")
    @RequiresPermissions("task:getTasksInTaskSea")
    public Msg getTasksInTaskSea(){
        try{
            List<Task> tasks = taskService.queryTasksInTaskSea();
            if (null == tasks || 0 >= tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 查询进行过废弃操作的这些任务
     *
     * @return
     */
    @GetMapping("getTasksInTrash")
    @RequiresPermissions("task:getTasksInTrash")
    public Msg getTasksInTrash(){
        try{
            List<Task> tasks = taskService.queryTasksInTrash();
            if (null == tasks||0 >= tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 根据公司Id查询任务
     *
     * @param comId
     * @return
     */
    @GetMapping("getTasksByComId/{comId}")
    @RequiresPermissions("task:getTasksByComId")
    public Msg getTasksByComId(@PathVariable @NotNull @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码") String comId){
        try{
            List<Task> tasks = taskService.queryTasksByComId(comId);
            if (null == tasks||0 >= tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 查看自己部门以及子部门所有员工的任务情况
     *
     * @param httpSession
     * @return
     */
    @GetMapping("getTasksUnderTheLeadership")
    @RequiresPermissions("task:getTasksUnderTheLeadership")
    public Msg getTasksUnderTheLeadership(HttpSession httpSession){
        try{
            User user = (User)httpSession.getAttribute("user");
            List<Task> tasks = taskService.queryTasksUnderTheLeadership(user.getOrgId());
            if (null == tasks||0 >= tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }


    }
    //* 根据社会统一信用代码精确查
    //     * 根据企业信息名称模糊查
    //     * 根据法人真实姓名模糊查
    //     * 根据其他人真实姓名模糊查
    //     * 根据电话号查
    /**
     * 模糊查询任务，其实是根据com详情查询task
     * @param words
     * @return
     */
    @GetMapping("getTasksByFuzzySearchAll")
    @RequiresPermissions("task:getTasksByFuzzySearchAll")
    public Msg getTasksByFuzzySearchAll(@RequestParam String words){
        try{
            List<Task> tasks = taskService.queryTasksByFuzzySearchAll(words);
            if (null == tasks||0 >= tasks.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(tasks);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }
}