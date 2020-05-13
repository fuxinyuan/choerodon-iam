package io.choerodon.iam.infra.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.hzero.iam.domain.entity.Tenant;

import io.choerodon.iam.api.vo.ProjectOverViewVO;
import io.choerodon.iam.api.vo.TenantVO;
import io.choerodon.mybatis.common.BaseMapper;

/**
 * @author wuguokai
 */
public interface TenantC7nMapper {

    List<TenantVO> fulltextSearch(
            @Param("name") String name,
            @Param("code") String code,
            @Param("enabled") Boolean enabled,
            @Param("params") String params);

    Set<TenantVO> selectFromMemberRoleByMemberId(@Param("memberId") Long memberId,
                                                 @Param("includedDisabled") Boolean includedDisabled);

    Set<TenantVO> selectOrgByUserAndPros(@Param("memberId") Long memberId,
                                         @Param("includedDisabled") Boolean includedDisabled);

    List<TenantVO> selectOrganizationsWithRoles(
            @Param("id") Long id,
            @Param("start") Integer start,
            @Param("size") Integer size,
            @Param("params") String params);

    List<Long> listMemberIds(@Param("orgId") Long orgId,
                             @Param("orgName") String orgName);


    Boolean organizationEnabled(@Param("sourceId") Long sourceId);

    List<TenantVO> selectByTenantIds(@Param("ids") Set<Long> ids);

    /**
     * 获取所有组织{id,name}
     *
     * @return 组织{id,name}
     */
    List<TenantVO> selectAllTenants();

    /**
     * 获取 指定id范围 的 组织简要信息
     *
     * @param orgIds  指定的组织范围
     * @param name    组织名查询参数
     * @param code    组织编码查询参数
     * @param enabled 组织启停用查询参数
     * @param params  全局模糊搜索查询参数
     * @return 查询结果
     */
    List<TenantVO> selectSpecified(@Param("orgIds") Set<Long> orgIds,
                                   @Param("name") String name,
                                   @Param("code") String code,
                                   @Param("enabled") Boolean enabled,
                                   @Param("params") String params);


    /**
     * 根据用户Id查询用户所属组织信息.
     *
     * @param userId 用户Id
     * @return 用户所属组织信息
     */
    TenantVO selectOwnOrgByUserId(@Param("userId") Long userId);

    ProjectOverViewVO projectOverview(@Param("organizationId") Long organizationId);

    List<Long> getoRoganizationByName(@Param("name") String name);

}