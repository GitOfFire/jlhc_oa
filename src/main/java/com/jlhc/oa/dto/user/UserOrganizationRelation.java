package com.jlhc.oa.dto.user;

import java.io.Serializable;

public class UserOrganizationRelation implements Serializable {
    private Integer userOrgId;

    private Integer userId;

    private Integer orgId;

    private static final long serialVersionUID = 1L;

    public Integer getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(Integer userOrgId) {
        this.userOrgId = userOrgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userOrgId=").append(userOrgId);
        sb.append(", userId=").append(userId);
        sb.append(", orgId=").append(orgId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserOrganizationRelation other = (UserOrganizationRelation) that;
        return (this.getUserOrgId() == null ? other.getUserOrgId() == null : this.getUserOrgId().equals(other.getUserOrgId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserOrgId() == null) ? 0 : getUserOrgId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        return result;
    }
}