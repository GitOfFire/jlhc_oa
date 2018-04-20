package com.jlhc.oa.dto.role;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

public class RoleGroup implements Serializable {
    //Max只有九位
    @Max(999999999)
    private Integer groupId;
    @NotNull
    @Length(min = 1, max = 64)
    private String groupName;
    //Max只有九位
    @NotNull
    @Max(999999999)
    private Integer orgId;

    @Max(9)
    private Integer groupDefState;

    private Set<Role> roles = new HashSet<>();

    private static final long serialVersionUID = 1L;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getGroupDefState() {
        return groupDefState;
    }

    public void setGroupDefState(Integer groupDefState) {
        this.groupDefState = groupDefState;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "RoleGroup{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", orgId=" + orgId +
                ", groupDefState=" + groupDefState +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleGroup roleGroup = (RoleGroup) o;
        return Objects.equals(groupId, roleGroup.groupId) &&
                Objects.equals(groupName, roleGroup.groupName) &&
                Objects.equals(orgId, roleGroup.orgId) &&
                Objects.equals(groupDefState, roleGroup.groupDefState) &&
                Objects.equals(roles, roleGroup.roles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupId, groupName, orgId, groupDefState, roles);
    }

}