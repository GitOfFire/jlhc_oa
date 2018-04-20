package com.jlhc.oa.dto.function;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Module implements Serializable {

    @Max(999999999)
    private Integer moduleId;
    @Length(min = 1,max = 127)
    @NotEmpty
    private String moduleName;

    private static final long serialVersionUID = 1L;

}