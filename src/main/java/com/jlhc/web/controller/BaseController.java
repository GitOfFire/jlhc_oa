package com.jlhc.web.controller;

import com.jlhc.common.utils.ResultUtil;
import com.jlhc.common.dto.Msg;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * controller基础类，处理异常,权限异常,参数校验异常
 *
 * @author renzhong
 * @version 1.0 ,created in 2017-12-28 14:13
 */
public class BaseController {

    public static final Logger logger =  LoggerFactory.getLogger(BaseController.class);

    /**
     * 认证异常
     *
     * @param request
     * @param response
     * @return Msg 错误消息
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public Msg authenticationException(HttpServletRequest request, HttpServletResponse response) {
        return ResultUtil.error("401","权限认证失败");
    }

    /**
     * 权限异常
     *
     * @param request
     * @param response
     * @return Msg 错误消息
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public Msg authorizationException(HttpServletRequest request, HttpServletResponse response) {
        return ResultUtil.error("402","权限校验异常");
    }

    /**
     * 传入参数校验异常
     *
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Msg NotValidException(HttpServletRequest request, HttpServletResponse response,MethodArgumentNotValidException e){

        return ResultUtil.error("301",e.getMessage());
    }
    /*@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Msg HttpRequestMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response,HttpRequestMethodNotSupportedException e){
        return ResultUtil.error(e);
    }*/

    //该方法已经被VaildApspect实现,故而停用,closedTime:2018-1-10 15:30
    /**
     * 处理BingResult方法
     *
     * @param bindingResult
     * @return map ,key:字段名;value:错误默认信息,由hibernate vaild 提供
     */
    /*protected Map<String, String> getVaildErrorMap(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String,String> vaildErrorMap = new HashMap<String, String>();
        for (FieldError fieldError:fieldErrors){
            vaildErrorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return vaildErrorMap;
    }*/

    /**
     * 统一的catch错误处理
     *
     * @param e
     * @return
     */
    public Msg errorResultOperation(Exception e){
        logger.error(e.getMessage(),e);
        return ResultUtil.error(e);
    }

}
