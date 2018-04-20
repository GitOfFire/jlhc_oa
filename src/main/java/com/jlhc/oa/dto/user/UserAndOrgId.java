package com.jlhc.oa.dto.user;

import com.jlhc.oa.dto.user.User;

import javax.validation.Valid;
import javax.validation.constraints.Max;

/**
 * 用户封装用户信息,所属组织机构信息,并进行基础校验
 *
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 12:06 2018/1/16 0016
 */
public class UserAndOrgId {
    @Valid
    private User user;
    @Max(value = 999999999)
    Integer orgId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "UserAndOrgId{" +
                "user=" + user +
                ", orgId=" + orgId +
                '}';
    }
}
