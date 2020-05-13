package io.choerodon.iam.app.service;

import io.choerodon.core.domain.Page;
import io.choerodon.core.oauth.CustomUserDetails;
import io.choerodon.iam.api.vo.TenantVO;
import io.choerodon.iam.api.vo.UserNumberVO;
import io.choerodon.iam.api.vo.UserWithGitlabIdVO;
import io.choerodon.iam.infra.dto.ProjectDTO;
import io.choerodon.iam.infra.dto.UserInfoDTO;
import io.choerodon.iam.infra.dto.UserWithGitlabIdDTO;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.iam.api.dto.UserPasswordDTO;
import org.hzero.iam.domain.entity.MemberRole;
import org.hzero.iam.domain.entity.Role;
import org.hzero.iam.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * @author scp
 * @date 2020/4/1
 * @description
 */
public interface UserC7nService {

    User queryInfo(Long userId);

    User updateInfo(User user, Boolean checkLogin);

    CustomUserDetails checkLoginUser(Long id);

    String uploadPhoto(Long id, MultipartFile file);

    String savePhoto(Long id, MultipartFile file, Double rotate, Integer axisX, Integer axisY, Integer width, Integer height);

    void check(User user);

    /**
     * 根据用户id集合查询用户的集合
     *
     * @param ids         用户id数组
     * @param onlyEnabled 默认为true，只查询启用的用户
     * @return List<User> 用户集合
     */
    List<User> listUsersByIds(Long[] ids, Boolean onlyEnabled);

    /**
     * 根据用户id集合查询用户的集合
     *
     * @param ids         用户id
     * @param onlyEnabled 默认为true，只查询启用的用户
     * @return List<User> 用户集合
     */
    List<UserWithGitlabIdVO> listUsersByIds(Set<Long> ids, Boolean onlyEnabled);

    /**
     * 根据用户emails集合查询用户的集合
     *
     * @param emails 用户email数组
     * @return List<User> 用户集合
     */
    List<User> listUsersByEmails(String[] emails);


    /**
     * 根据loginName集合查询所有用户
     */
    List<User> listUsersByLoginNames(String[] loginNames, Boolean onlyEnabled);

    Long queryOrgIdByEmail(String email);

    Map<String, Object> queryAllAndNewUsers();


    /**
     * 按时间段统计组织或平台人数
     *
     * @param organizationId 如果为null，则统计平台人数
     * @param startTime
     * @param endTime
     * @return
     */
    UserNumberVO countByDate(Long organizationId, Date startTime, Date endTime);


    /**
     * 组织层分页查询用户列表（包括用户信息以及所分配的组织角色信息）.
     *
     * @return 用户列表（包括用户信息以及所分配的组织角色信息）
     */
    List<UserWithGitlabIdDTO> listUsersWithRolesAndGitlabUserIdByIdsInOrg(Long organizationId, Set<Long> userIds);

    /**
     * 查询组织下用户的项目列表.
     * 1. admin用户和组织管理员 可查看当前组织所有项目; 普通用户 只能查看分配了权限的启用项目
     * 2. root用户可进入所有项目; 组织管理员和普通用户需分配权限才能进入项目
     *
     * @param organizationId 组织Id
     * @param userId         用户Id
     * @param projectDTO     项目DTO
     * @param params         模糊查询字段
     * @return 项目列表
     */
    List<ProjectDTO> listProjectsByUserId(Long organizationId, Long userId, ProjectDTO projectDTO, String params);

    Page<User> pagingQueryAdminUsers(PageRequest pageRequest, String loginName, String realName, String params);

    void addAdminUsers(long[] ids);

    void deleteAdminUser(long id);


    Page<TenantVO> pagingQueryOrganizationsWithRoles(PageRequest pageRequest,
                                                     Long id, String params);

    Page<ProjectDTO> pagingQueryProjectAndRolesById(PageRequest pageRequest,
                                                    Long id, String params);


    /**
     * 校验用户是否是root用户
     *
     * @param id
     * @return
     */
    Boolean checkIsRoot(Long id);

//    /**
//     * 校验用户是否是组织Root用户
//     *
//     * @param organizationId 组织id
//     * @param userId         用户id
//     * @return true表示是
//     */
//    Boolean checkIsOrgRoot(Long organizationId, Long userId);

    List<ProjectDTO> queryProjects(Long userId, Boolean includedDisabled);


    /**
     * 全局层分页查询用户列表（包括用户信息以及所分配的全局角色信息）.
     *
     * @return 用户列表（包括用户信息以及所分配的全局角色信息）
     */
    Page<User> pagingQueryUsersWithRolesOnSiteLevel(PageRequest pageRequest, String orgName, String loginName, String realName,
                                                    String roleName, Boolean enabled, Boolean locked, String params);


    /**
     * 校验用户是否是项目的所有者
     *
     * @param id
     * @param projectId
     * @return true 是
     */
    Boolean checkIsGitlabOwner(Long id, Long projectId, String level);

    /**
     * 异步
     * 向用户发送通知（包括邮件和站内信）
     *
     * @param fromUserId 发送通知的用户
     * @param userIds    接受通知的目标用户
     * @param code       业务code
     * @param params     渲染参数
     * @param sourceId   触发发送通知对应的组织/项目id，如果是site层，可以为0或null
     */
    Future<String> sendNotice(Long fromUserId, List<Long> userIds, String code, Map<String, Object> params, Long sourceId);
    // TODO notify-service
//    Future<String> sendNotice(Long fromUserId, List<Long> userIds, String code, Map<String, Object> params, Long sourceId, WebHookJsonSendDTO webHookJsonSendDTO);
//
//    Future<String> sendNotice(Long fromUserId, List<Long> userIds, String code, Map<String, Object> params, Long sourceId, boolean sendAll, WebHookJsonSendDTO webHookJsonSendDTO);
//    /**
//     * 单独发送webhook
//     *
//     * @param code
//     * @param sourceId
//     * @param webHookJsonSendDTO
//     * @return
//     */
//    Future<String> sendNotice(String code, Long sourceId, WebHookJsonSendDTO webHookJsonSendDTO);
//
//    Future<String> sendNotice(Long fromUserId, Map<Long, Set<Long>> longSetMap, String code, Map<String, Object> params, Long sourceId);

    /**
     * 校验用户是否是组织Root用户
     *
     * @param organizationId
     * @param userId
     * @return
     */
    Boolean checkIsOrgRoot(Long organizationId, Long userId);

    // TODO notifyservice
//    WebHookJsonSendDTO.User getWebHookUser(Long userId);

    /**
     * 创建用户角色.
     *
     * @param userDTO          用户DTO
     * @param roleList         角色列表
     * @param sourceType       资源层级
     * @param sourceId         资源Id
     * @param isEdit           是否为新建操作: 如果为false,则只插入; 否则既插入也删除
     * @param allowRoleEmpty   是否允许用户角色为空
     * @param allowRoleDisable 是否允许用户角色为禁用
     * @return 用户角色DTO列表
     */
    List<MemberRole> createUserRoles(User userDTO, List<Role> roleList, String sourceType, Long sourceId,
                                     boolean isEdit, boolean allowRoleEmpty, boolean allowRoleDisable);

    List<MemberRole> createUserRoles(User userDTO, List<Role> roleDTOList, String sourceType, Long sourceId,
                                     boolean isEdit, boolean allowRoleEmpty, boolean allowRoleDisable, Boolean syncAll);


    /**
     * 更新用户角色.
     *
     * @param userId     用户Id
     * @param sourceType 资源层级
     * @param sourceId   资源Id
     * @param roleList   角色列表
     * @return 用户DTO
     */
    User updateUserRoles(Long userId, String sourceType, Long sourceId, List<Role> roleList);

    User updateUserRoles(Long userId, String sourceType, Long sourceId, List<Role> roleList, Boolean syncAll);

    /**
     * 在全局层/组织层/项目层 批量分配给用户角色.
     *
     * @param sourceType     资源层级
     * @param sourceId       资源层级
     * @param memberRoleList 用户角色列表
     * @return 用户角色DTO列表
     */
    List<MemberRole> assignUsersRoles(String sourceType, Long sourceId, List<MemberRole> memberRoleList);

    /**
     * 查询项目下指定角色的用户列表
     *
     * @param projectId
     * @param roleLable
     * @return
     */
    List<User> listProjectUsersByProjectIdAndRoleLable(Long projectId, String roleLable);

    /**
     * 根据projectId和param模糊查询loginName和realName两列
     *
     * @param projectId
     * @param param
     * @return
     */
    List<User> listUsersByName(Long projectId, String param);

    /**
     * 查询项目下的项目所有者
     *
     * @param projectId
     * @return
     */
    List<User> listProjectOwnerById(Long projectId);

    /**
     * 项目层分页查询用户列表（包括用户信息以及所分配的项目角色信息）.
     *
     * @return 用户列表（包括用户信息以及所分配的项目角色信息）
     */
    List<UserWithGitlabIdDTO> listUsersWithRolesAndGitlabUserIdByIdsInProject(Long projectId, Set<Long> userIds);

    /**
     * 项目层查询用户列表（包括用户信息以及所分配的项目角色信息）排除自己.
     *
     * @return 用户列表（包括用户信息以及所分配的项目角色信息）
     */
    List<User> listUsersWithRolesOnProjectLevel(Long projectId, String loginName, String realName, String roleName, String params);

    Boolean checkEnableCreateUser(Long projectId);

    UserInfoDTO updateUserInfo(Long id, UserInfoDTO userInfoDTO);

    void selfUpdatePassword(Long userId, UserPasswordDTO userPasswordDTO, Boolean checkPassword, Boolean checkLogin);

    User updateUserDisabled(Long userId);
}