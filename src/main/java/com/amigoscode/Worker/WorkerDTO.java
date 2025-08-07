package com.amigoscode.Worker;

import org.antlr.v4.runtime.misc.NotNull;

public class WorkerDTO {
    @NotNull
    private String fullname;

    @NotNull
    private Integer departmentId;

    @NotNull
    private Integer roleId;

    //1.
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    //2.
    public Integer getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }


    //3/
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
