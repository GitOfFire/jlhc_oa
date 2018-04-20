package com.jlhc.oa.dto.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RoleIdAndFuncIds {
    @NotNull
    @Max(999999999)
    Integer roleId;
    //@NotNull
    @Size(min=1)
    List<Integer> funcIds;
}
