package com.jlhc.common.conf;

import com.jlhc.web.filter.ActionAuthenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 这里可以配置自定义的Filter
 *
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 12:23 2018/1/18 0018
 */
@Configuration
public class FilterConfiguration {

    /**
     * 编码字符集统一
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);

        return registrationBean;
    }

    /**
     * 跨域请求头统一
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean actionAuthenFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        ActionAuthenFilter actionAuthenFilter = new ActionAuthenFilter();
        registrationBean.setFilter(actionAuthenFilter);
        return registrationBean;
    }



}
