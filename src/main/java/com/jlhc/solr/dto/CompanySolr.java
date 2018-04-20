package com.jlhc.solr.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * 存储在solr中的公司信息数据结构
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompanySolr {
    @Field
    private String comId;
//    @NotEmpty
//    @Length(max = 255)
    @Field
    private String comUnicode;
//    @NotEmpty
//    @Length(max = 255)
    @Field
    private String comName;
//    @Max(4)
//    @Min(1)
//    @NotNull
    //private Integer comType;
//    @NotEmpty
//    @Length(max = 255)
 //   private String comAddress;
//    @NotEmpty
//    @Length(max = 32,min = 32,message = "长度必须是32")
 //   private String cusId;
//    @NotNull
    //@Max(20)
    @Field
    private String cusName;
    @Field
    private String otherCusNames;//以分号间隔

    //private Long comCapital;
    //@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
//    @Past(message="必须是一个过去的日期")//这里是否还要添加其他属性值
    //private Date comFoundTime;
//    @NotNull
//    @Max(20)
    //private Long comBusinessTerm;//营业期限
//    @NotEmpty
//    @Length(max = 255)
//    @Field
//    private String comBussinessScope;//经营范围
//
////    @Length(max = 255)
//    @Field
//    private String comDescription;
}
