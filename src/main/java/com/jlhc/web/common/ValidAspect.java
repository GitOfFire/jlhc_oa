package com.jlhc.web.common;

import com.jlhc.ApplicationJlhc;
import com.jlhc.common.utils.ResultUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 校验切面,运用spring AOP技术实现
 * notes:   输入参数为单个基本类型参数时,直接在注解中加校验,
 *          参数封装成bean数据类型时,校验注解加在bean中,而且,一定要在后面紧跟bindingResult
 *
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 10:11 2018/1/10 0010
 */
@Aspect
@Component
public class ValidAspect {

    private ObjectError error;

    private final static Logger logger = LoggerFactory.getLogger(ApplicationJlhc.class);

    //切入点是所有的controller
    @Pointcut(value = "execution(public * com.jlhc.web.controller.*.*(..))")
    public void valid(){
    }

    //环绕通知,环绕增强，相当于MethodInterceptor?为什么不用before?
    @Around("valid()")
    public Object arround(ProceedingJoinPoint pjp){
        //logger.info("ValidAspectAOP..Start..");
        try {
            //取参数,没有参数就不校验了
            Object[] objects = pjp.getArgs();
            if (objects.length == 0){
                return pjp.proceed();
            }
            /**************************校验封装好的javabean**********************/
            //寻找带BindingResult参数的方法，然后判断是否有error，如果有则是校验不通过
            for (Object object : objects){
                if (object instanceof BeanPropertyBindingResult){
                    //有校验
                    BeanPropertyBindingResult result = (BeanPropertyBindingResult)object;
                    if (result.hasErrors()){
                        List<FieldError> fieldErrors = result.getFieldErrors();
                        Map<String,String> vaildErrorMap = new HashMap<String, String>();
                        for (FieldError fieldError:fieldErrors){
                            vaildErrorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                        }
                        //logger.info("vaildErrorMap.....:" + vaildErrorMap.toString());
                        return ResultUtil.vaildError(vaildErrorMap);//这里很可能不行

                    }
                }
            }
            /**************************校验普通参数*************************/
            //  获得切入目标对象
            Object target = pjp.getThis();
            //获得切入方法
            Method method = ((MethodSignature) pjp.getSignature()).getMethod();
            //执行校验,获得校验结果
            Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, objects);
            //如果有校验不通过的
            if (!validResult.isEmpty()) {
                String[] parameterNames = parameterNameDiscoverer.getParameterNames(method); // 获得方法的参数名称
                Map<String,String> vaildErrorMap = new HashMap<String, String>();
                for(ConstraintViolation<Object> constraintViolation : validResult) {
                    PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();  // 获得校验的参数路径信息
                    int paramIndex = pathImpl.getLeafNode().getParameterIndex(); // 获得校验的参数位置
                    String paramName = parameterNames[paramIndex];  // 获得校验的参数名称

                    //System.out.println(paramName);
                    //System.out.println(constraintViolation.getMessage());
                    vaildErrorMap.put(paramName,constraintViolation.getMessage());
                    //校验信息
                }
                //返回第一条
                //return validResult.iterator().next().getMessage();
                //返回所有
                return ResultUtil.vaildError(vaildErrorMap);
            }
            return pjp.proceed();//切面编程,此处切记放行
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }


    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = factory.getValidator().forExecutables();


    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }
}
