package com.jlhc.common.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//这两个配置先不能加,加上会修改spring的默认扫描位置
//@EnableWebMvc
//@ComponentScan(basePackages = {"com.jlhc.web.controller"})

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    /*@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.jlhc.web.controller"))
            .paths(PathSelectors.any())
            .build();
    }*/

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("建利衡创OA---API")
            .description("用于我司前后端同志敏捷开发时统一接口")
                //.licenseUrl("http://localhost:8081")
           // .termsOfServiceUrl("http://localhost")
            //.termsOfServiceUrl("#")
            .version("1.0")
            .build();
    }

    @Bean

    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(apiInfo())

                .select()

                .apis(RequestHandlerSelectors.basePackage("com.jlhc.web.controller"))

                .paths(PathSelectors.any())

                .build();

    }
}
