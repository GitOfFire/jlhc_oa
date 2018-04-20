package com.jlhc.oa.dto.role;

import com.jlhc.oa.dto.function.Right;
import com.jlhc.oa.dto.user.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Role implements Serializable {
    @Max(999999999)
    private Integer roleId;

    @NotNull
    @Max(999999999)
    private Integer roleGroupId;

    @NotEmpty(message = "角色名不能为空")
    @Length(max = 64)
    private String roleName;

    @Past
    private Date roleCreatedtime;
    @Length(max = 200)
    private String roleDescription;
    @Max(value = 3, message = "角色无此状态类型")
    @Min(value = 0, message = "角色无此状态类型")
    private Integer roleDatarange;

    private Set<Right> rights = new HashSet<>();

    private Set<User> users = new HashSet<>();

    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleGroupId() {
        return roleGroupId;
    }

    public void setRoleGroupId(Integer roleGroupId) {
        this.roleGroupId = roleGroupId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getRoleCreatedtime() {
        return roleCreatedtime;
    }

    public void setRoleCreatedtime(Date roleCreatedtime) {
        this.roleCreatedtime = roleCreatedtime;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
    }

    public Integer getRoleDatarange() {
        return roleDatarange;
    }

    public void setRoleDatarange(Integer roleDatarange) {
        this.roleDatarange = roleDatarange;
    }

    public Set<Right> getRights() {
        return rights;
    }

    public void setRights(Set<Right> rights) {
        this.rights = rights;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                Objects.equals(roleGroupId, role.roleGroupId) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(roleCreatedtime, role.roleCreatedtime) &&
                Objects.equals(roleDescription, role.roleDescription) &&
                Objects.equals(roleDatarange, role.roleDatarange) &&
                Objects.equals(rights, role.rights) &&
                Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, roleGroupId, roleName, roleCreatedtime, roleDescription, roleDatarange, rights, users);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleGroupId=" + roleGroupId +
                ", roleName='" + roleName + '\'' +
                ", roleCreatedtime=" + roleCreatedtime +
                ", roleDescription='" + roleDescription + '\'' +
                ", roleDatarange=" + roleDatarange +
                ", rights=" + rights +
                ", users=" + users +
                '}';
    }
}