package com.jlhc.common.conf;

import com.jlhc.ApplicationJlhc;
import com.jlhc.web.shiro.AuthRealm;
import com.jlhc.web.shiro.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;

/**
 * shiro的配置类
 * 增加configuration注解，注入spring中
 *
 * @author renzhong
 * @version 1.0 , 2017年12月22日，17点25分
 */
@Configuration
public class ShiroConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationJlhc.class);

    /**
     * 缓存管理器
     * @return
     */
    @Bean(value="ehCacheManager")
    public EhCacheManager ehCacheManager(/*@Qualifier("ehCacheManagerFactoryBean") EhCacheManagerFactoryBean bean*/) {
        logger.info("ehCacheManager()");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager((org.apache.shiro.mgt.SecurityManager) securityManager);
        //配置登录的url和登录成功的url,此处设置的url与最终登陆请求的url是一致的
        bean.setLoginUrl("/login.do");
        bean.setSuccessUrl("/home");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        //filterChainDefinitionMap.put("/jsp/login.jsp*", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/login.do*", "anon"); //表示可以匿名访问,此处配置的是登陆的url,此处为登陆接口
        filterChainDefinitionMap.put("/logout.do*","anon");


        filterChainDefinitionMap.put("/user/*.do", "authc");
        filterChainDefinitionMap.put("/rolegroup/*.do", "authc");
        //filterChainDefinitionMap.put("/loginUser", "anon");
        //filterChainDefinitionMap.put("/jsp/error.jsp*","anon");
        //filterChainDefinitionMap.put("/jsp/index.jsp*","authc");
        //filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
        //filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
        //filterChainDefinitionMap.put("/*.*", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        SecurityManager manager1 = manager;
        return manager1;
    }
    //配置自定义的权限登录器，其中注入自定义的密码比较器，比较器方法的执行实在认证方法执行后
    @Bean(name="authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher, @Qualifier("ehCacheManager") EhCacheManager ehCacheManager) {
        AuthRealm authRealm=new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        authRealm.setCacheManager(ehCacheManager);
        return authRealm;
    }
    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager((org.apache.shiro.mgt.SecurityManager) manager);
        return advisor;
    }
}
