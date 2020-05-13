package io.choerodon.iam.api.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import org.hzero.iam.domain.entity.Role;
import org.hzero.iam.domain.entity.Tenant;

import io.choerodon.iam.infra.dto.ProjectDTO;

/**
 * @author scp
 * @date 2020/4/21
 * @description
 */
public class TenantVO extends Tenant {

    @ApiModelProperty(value = "组织信息")
    private TenantConfigVO tenantConfigVO;

    private List<ProjectDTO> projects;

    @ApiModelProperty(value = "项目数量")
    private Integer projectCount;

    @ApiModelProperty(value = "用户数量")
    private Integer userCount;

    private List<Role> roles;

    private String ownerLoginName;

    private String ownerRealName;

    private String ownerPhone;

    private String ownerEmail;

    private Boolean isInto = false;

    public Boolean getInto() {
        return isInto;
    }

    public void setInto(Boolean into) {
        isInto = into;
    }

    public TenantConfigVO getTenantConfigVO() {
        return tenantConfigVO;
    }

    public void setTenantConfigVO(TenantConfigVO tenantConfigVO) {
        this.tenantConfigVO = tenantConfigVO;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getOwnerLoginName() {
        return ownerLoginName;
    }

    public void setOwnerLoginName(String ownerLoginName) {
        this.ownerLoginName = ownerLoginName;
    }

    public String getOwnerRealName() {
        return ownerRealName;
    }

    public void setOwnerRealName(String ownerRealName) {
        this.ownerRealName = ownerRealName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}