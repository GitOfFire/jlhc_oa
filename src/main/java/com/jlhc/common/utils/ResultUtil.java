package com.jlhc.common.utils;

import com.jlhc.common.exception.FailVertifyException;
import com.jlhc.common.exception.NotVertifyException;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.common.dto.Msg;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.solr.client.solrj.SolrServerException;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 结果封装工具
 */
public class ResultUtil {

    /**
     * 200-成功
     */
    public final static String CODE_SUCCESS = "200";

    /**
     *  301-代表永久性转移
     */
    public final static String CODE_PERMANENTLY_MOVED = "301";


    /**
     * 500-业务逻辑错误
     */
    public final static String CODE_ERROR_SERVICE = "500";

    /**
     * 501-功能不完善，无对应方法
     */
    public final static String CODE_ERROR_FUNCTION = "501";

    /**
     * 502-网络异常
     */
    public final static String CODE_ERROR_WEB = "502";

    /**
     * 503-数据校验异常
     */
    public final static String CODE_ERROR_CHECK = "503";

    /**
     * 504-未知其它
     */
    public final static String CODE_ERROR_OTHER = "504";
    private static Exception ex;

    //下面开始处理访问成功的数据

    /**
     * 自定义成功处理
     *
     * @param message
     * @return
     */
    @NotNull
    public static Msg success(String message){
        return new Msg(CODE_SUCCESS,message);
    }

    /**
     * 有返回数据,自定义成功处理
     *
     * @param message
     * @param data
     * @return
     */
    @NotNull
    public static Msg success(String message, Object data){
        return new Msg(CODE_SUCCESS,message,data);
    }

    @NotNull
    public static Msg success(){
        return new Msg(CODE_SUCCESS);
    }

    @NotNull
    public static Msg addSuccess(){
        return new Msg(CODE_SUCCESS,"新增成功");
    }

    @NotNull
    public static Msg addSuccess(Object data){
        return new Msg(CODE_SUCCESS,"新增成功",data);
    }

    @NotNull
    public static Msg updateSuccess(){ return new Msg(CODE_SUCCESS,"更新成功");}

    @NotNull
    public static Msg updateSuccess(Object data){
        return new Msg(CODE_SUCCESS,"更新成功",data);
    }

    @NotNull
    public static Msg deleteSuccess(){
        return new Msg(CODE_SUCCESS,"删除成功");
    }

    @NotNull
    public static Msg deleteSuccess(Object data){
        return new Msg(CODE_SUCCESS,"删除成功",data);
    }

    /**
     * 查询返回对象处理上
     *
     * @param data 查询结果
     * @return 封装
     */
    @NotNull
    public static Msg selectSuccess(Object data){
        return new Msg(CODE_SUCCESS,"查询成功",data);
    }

    @NotNull
    public static Msg operationSuccess(){
        return new Msg(CODE_SUCCESS,"操作成功");
    }

    @NotNull
    public static Msg operationSuccess(Object data){
        return new Msg(CODE_SUCCESS,"操作成功",data);
    }



    //开始处理异常

    /**
     * 根据抛出异常的类别识别异常,不包括权限异常
     */
    @NotNull
    private static String getErrorMessage(Exception ex) {
        ResultUtil.ex = ex;
        ex.printStackTrace();
        if (ex instanceof ArithmeticException) {
            return "系统异常：计算错误";
        }
        if (ex instanceof NullPointerException) {
            return "系统异常：输入错误，缺少输入值";
        }
        if (ex instanceof ClassCastException) {
            return "系统异常：类型转换错误";
        }
        if (ex instanceof NegativeArraySizeException) {
            return "系统异常：集合负数";
        }
        if (ex instanceof ArrayIndexOutOfBoundsException) {
            return "系统异常：集合超出范围";
        }
        if (ex instanceof FileNotFoundException) {
            return "系统异常：文件未找到";
        }
        if (ex instanceof NumberFormatException) {
            return "系统异常：输入数字错误";
        }
        if (ex instanceof SQLException) {
            return "系统异常：数据库异常";
        }
        if (ex instanceof IOException) {
            return "系统异常：文件读写错误";
        }
        if (ex instanceof NoSuchMethodException) {
            return "系统异常：方法找不到";
        }
        if(ex instanceof NotVertifyException){
            return "账户异常:账号正在审核中...不允许登录";
        }
        if(ex instanceof FailVertifyException){
            return "账户异常:账号未通过审核,不允许登录";
        }
        if(ex instanceof UnknownAccountException){
            return "账户异常:账号不存在,或者账户名数据库重名";
        }

        if(ex instanceof IncorrectCredentialsException){
            return "密码错误";
        }
        if (ex instanceof SolrServerException){
            return "系统异常:solr数据同步错误";
        }

        if (ex instanceof NullEntityInDatabaseException){
            return "数据异常:数据库中没有要处理的实体数据";
        }
        return "未知异常:" + ex.getMessage();
    }

    /**
     * 服务端异常
     *
     * @param e
     * @return
     */
    public static Msg error(Exception e){
        Msg msg=new Msg();
        msg.setCode(CODE_ERROR_SERVICE);
        msg.setMsg(getErrorMessage(e));
        return msg;
    }

    /**
     * 双参异常处理,data为null
     *
     * @param code 手输入错误代码
     * @param message 手输消息
     * @return
     */
    @NotNull
    public static Msg error(String code, String message){
        return new Msg(code,message);
    }

    /**
     * 自定义编码过程中可能出现的异常
     *
     * @param Message
     * @return
     */
    @NotNull
    public static Msg error(String Message){
        return new Msg(CODE_ERROR_OTHER,Message);
    }

    /**
     * 用于处理校验异常
     *
     * @param data
     * @return
     */
    @NotNull
    public static Msg vaildError(Object data){
        return new Msg(CODE_ERROR_CHECK,"数据校验异常",data);
    }

    /**
     * 数据操作数据操作失败
     *
     * @return
     */
    @NotNull
    public static Msg operationFailed(){
        return new Msg(CODE_ERROR_SERVICE,"数据操作失败");
    }

    /**
     * 操作失败
     *
     * @param message 失败原因,有明确失败原因的都是后端进行参数校验确定的
     * @return
     */
    @NotNull
    public static Msg operationFailed(String message) {
        return new Msg(CODE_ERROR_CHECK,message);
    }

    /**
     * 未查询到数据
     *
     * @return
     */
    @NotNull
    public static Msg selectFailed(){
        return new Msg("200","未查询到数据");
    }
}
