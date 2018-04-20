package com.jlhc.sell.dto;

import java.io.Serializable;
import java.util.Date;

public class TaskForCreate implements Serializable {
    private String taskId;

    private String comId;

    private String taskContent;

    private Integer taskCreatedUser;

    private Date taskCreatedTime;

    private Integer holdUserId;

    private Integer taskState;

    private static final long serialVersionUID = 1L;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent == null ? null : taskContent.trim();
    }

    public Integer getTaskCreatedUser() {
        return taskCreatedUser;
    }

    public void setTaskCreatedUser(Integer taskCreatedUser) {
        this.taskCreatedUser = taskCreatedUser;
    }

    public Date getTaskCreatedTime() {
        return taskCreatedTime;
    }

    public void setTaskCreatedTime(Date taskCreatedTime) {
        this.taskCreatedTime = taskCreatedTime;
    }

    public Integer getHoldUserId() {
        return holdUserId;
    }

    public void setHoldUserId(Integer holdUserId) {
        this.holdUserId = holdUserId;
    }

    public Integer getTaskState() {
        return taskState;
    }

    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskId=").append(taskId);
        sb.append(", comId=").append(comId);
        sb.append(", taskContent=").append(taskContent);
        sb.append(", taskCreatedUser=").append(taskCreatedUser);
        sb.append(", taskCreatedTime=").append(taskCreatedTime);
        sb.append(", holdUserId=").append(holdUserId);
        sb.append(", taskState=").append(taskState);
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
        TaskForCreate other = (TaskForCreate) that;
        return (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getComId() == null ? other.getComId() == null : this.getComId().equals(other.getComId()))
            && (this.getTaskContent() == null ? other.getTaskContent() == null : this.getTaskContent().equals(other.getTaskContent()))
            && (this.getTaskCreatedUser() == null ? other.getTaskCreatedUser() == null : this.getTaskCreatedUser().equals(other.getTaskCreatedUser()))
            && (this.getTaskCreatedTime() == null ? other.getTaskCreatedTime() == null : this.getTaskCreatedTime().equals(other.getTaskCreatedTime()))
            && (this.getHoldUserId() == null ? other.getHoldUserId() == null : this.getHoldUserId().equals(other.getHoldUserId()))
            && (this.getTaskState() == null ? other.getTaskState() == null : this.getTaskState().equals(other.getTaskState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getComId() == null) ? 0 : getComId().hashCode());
        result = prime * result + ((getTaskContent() == null) ? 0 : getTaskContent().hashCode());
        result = prime * result + ((getTaskCreatedUser() == null) ? 0 : getTaskCreatedUser().hashCode());
        result = prime * result + ((getTaskCreatedTime() == null) ? 0 : getTaskCreatedTime().hashCode());
        result = prime * result + ((getHoldUserId() == null) ? 0 : getHoldUserId().hashCode());
        result = prime * result + ((getTaskState() == null) ? 0 : getTaskState().hashCode());
        return result;
    }
}