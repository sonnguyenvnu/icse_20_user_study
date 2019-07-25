package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.constant.UserTypeEnum;
import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.AdminService;
import cn.iocoder.mall.admin.api.bo.admin.AdminAuthenticationBO;
import cn.iocoder.mall.admin.api.bo.admin.AdminAuthorizationBO;
import cn.iocoder.mall.admin.api.bo.admin.AdminBO;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.bo.role.RoleBO;
import cn.iocoder.mall.admin.api.constant.AdminConstants;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.dto.admin.*;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2CreateTokenDTO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2RemoveTokenByUserDTO;
import cn.iocoder.mall.admin.convert.AdminConvert;
import cn.iocoder.mall.admin.dao.AdminMapper;
import cn.iocoder.mall.admin.dao.AdminRoleMapper;
import cn.iocoder.mall.admin.dataobject.AdminDO;
import cn.iocoder.mall.admin.dataobject.AdminRoleDO;
import cn.iocoder.mall.admin.dataobject.RoleDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.AdminService.version}")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private OAuth2ServiceImpl oauth2Service;
    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public AdminAuthenticationBO authentication(AdminAuthenticationDTO adminAuthenticationDTO) {
        AdminDO admin = adminMapper.selectByUsername(adminAuthenticationDTO.getUsername());
        // 账�?��?存在
        if (admin == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_USERNAME_NOT_REGISTERED.getCode());
        }
        // 密�?�?正确
        if (!encodePassword(adminAuthenticationDTO.getPassword()).equals(admin.getPassword())) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_PASSWORD_ERROR.getCode());
        }
        // 账�?�被�?用
        if (CommonStatusEnum.DISABLE.getValue().equals(admin.getStatus())) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_IS_DISABLE.getCode());
        }
        // 创建 accessToken
        OAuth2AccessTokenBO accessTokenBO = oauth2Service.createToken(new OAuth2CreateTokenDTO().setUserId(admin.getId())
            .setUserType(UserTypeEnum.ADMIN.getValue()));
        // 转�?�返回
        return AdminConvert.INSTANCE.convert2(admin).setToken(accessTokenBO);
    }

    @Override
    public PageResult<AdminBO> getAdminPage(AdminPageDTO adminPageDTO) {
        IPage<AdminDO> page = adminMapper.selectPage(adminPageDTO);
        return AdminConvert.INSTANCE.convert(page);
    }

    @Override
    public AdminBO addAdmin(Integer adminId, AdminAddDTO adminAddDTO) {
        // 校验账�?�唯一
        if (adminMapper.selectByUsername(adminAddDTO.getUsername()) != null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_USERNAME_EXISTS.getCode());
        }
        // �?存到数�?�库
        AdminDO admin = AdminConvert.INSTANCE.convert(adminAddDTO)
                .setPassword(encodePassword(adminAddDTO.getPassword())) // 加密密�?
                .setStatus(CommonStatusEnum.ENABLE.getValue());
        admin.setCreateTime(new Date());
        admin.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        adminMapper.insert(admin);
        // TODO �?�入�?作日志
        // 返回�?功
        return AdminConvert.INSTANCE.convert(admin);
    }

    @Override
    public Boolean updateAdmin(Integer adminId, AdminUpdateDTO adminUpdateDTO) {
        // 校验账�?�存在
        AdminDO admin = adminMapper.selectById(adminUpdateDTO.getId());
        if (admin == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_USERNAME_NOT_REGISTERED.getCode());
        }
        if (AdminConstants.USERNAME_ADMIN.equals(admin.getUsername())
                || AdminConstants.USERNAME_DEMO.equals(admin.getUsername())) { // 特殊账�?�，�?�?许编辑
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_ADMIN_CAN_NOT_UPDATE.getCode());
        }
        // 校验账�?�唯一
        AdminDO usernameAdmin = adminMapper.selectByUsername(adminUpdateDTO.getUsername());
        if (usernameAdmin != null && !usernameAdmin.getId().equals(adminUpdateDTO.getId())) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_USERNAME_EXISTS.getCode());
        }
        // 更新到数�?�库
        AdminDO updateAdmin = AdminConvert.INSTANCE.convert(adminUpdateDTO);
        adminMapper.updateById(updateAdmin);
        // TODO �?�入�?作日志
        // 返回�?功
        return true;
    }

    @Override
    @Transactional
    public Boolean updateAdminStatus(Integer adminId, AdminUpdateStatusDTO adminUpdateStatusDTO) {
        // 校验账�?�存在
        AdminDO admin = adminMapper.selectById(adminUpdateStatusDTO.getId());
        if (admin == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_USERNAME_NOT_REGISTERED.getCode());
        }
        if (AdminConstants.USERNAME_ADMIN.equals(admin.getUsername())
            || AdminConstants.USERNAME_DEMO.equals(admin.getUsername())) { // 特殊账�?�，�?�?许编辑
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_ADMIN_STATUS_CAN_NOT_UPDATE.getCode());
        }
        // 如果状�?相�?�，则返回错误
        if (adminUpdateStatusDTO.getStatus().equals(admin.getStatus())) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_STATUS_EQUALS.getCode());
        }
        // 更新管�?�员状�?
        AdminDO updateAdmin = new AdminDO().setId(adminUpdateStatusDTO.getId()).setStatus(adminUpdateStatusDTO.getStatus());
        adminMapper.updateById(updateAdmin);
        // 如果是关闭管�?�员，则标记 token 失效。�?�则，管�?�员还�?�以继续蹦跶
        if (CommonStatusEnum.DISABLE.getValue().equals(adminUpdateStatusDTO.getStatus())) {
            oauth2Service.removeToken(new OAuth2RemoveTokenByUserDTO().setUserId(adminId).setUserType(UserTypeEnum.ADMIN.getValue()));
        }
        // TODO �?�入�?作日志
        // 返回�?功
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteAdmin(Integer adminId, Integer updateAdminId) {
        // 校验账�?�存在
        AdminDO admin = adminMapper.selectById(updateAdminId);
        if (admin == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_USERNAME_NOT_REGISTERED.getCode());
        }
        // �?�有�?用的账�?��?�?�以删除
        if (CommonStatusEnum.ENABLE.getValue().equals(admin.getStatus())) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_DELETE_ONLY_DISABLE.getCode());
        }
        // 标记删除 AdminDO
        adminMapper.deleteById(updateAdminId); // 标记删除
        // 标记删除 AdminRole
        adminRoleMapper.deleteByAdminId(updateAdminId);
        // TODO �?�入�?作日志
        // 返回�?功
        return true;
    }

    @Override
    public Map<Integer, Collection<RoleBO>> getAdminRolesMap(Collection<Integer> adminIds) {
        // 查询管�?�员拥有的角色关�?�数�?�
        List<AdminRoleDO> adminRoleList = adminRoleMapper.selectListByAdminIds(adminIds);
        if (adminRoleList.isEmpty()) {
            return Collections.emptyMap();
        }
        // 查询角色数�?�
        List<RoleBO> roleList = roleService.getRoleList(CollectionUtil.convertSet(adminRoleList, AdminRoleDO::getRoleId));
        Map<Integer, RoleBO> roleMap = CollectionUtil.convertMap(roleList, RoleBO::getId);
        // 拼接数�?�
        Multimap<Integer, RoleBO> result = ArrayListMultimap.create();
        adminRoleList.forEach(adminRole -> result.put(adminRole.getAdminId(), roleMap.get(adminRole.getRoleId())));
        return result.asMap();
    }

    @Override
    public List<RoleBO> getRoleList(Integer adminId) {
        // 查询管�?�员拥有的角色关�?�数�?�
        List<AdminRoleDO> adminRoleList = adminRoleMapper.selectByAdminId(adminId);
        if (adminRoleList.isEmpty()) {
            return Collections.emptyList();
        }
        // 查询角色数�?�
        return roleService.getRoleList(CollectionUtil.convertSet(adminRoleList, AdminRoleDO::getRoleId));
    }

    @Override
    @Transactional
    public Boolean assignAdminRole(Integer adminId, AdminAssignRoleDTO adminAssignRoleDTO) {
        // 校验账�?�存在
        AdminDO admin = adminMapper.selectById(adminAssignRoleDTO.getId());
        if (admin == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_USERNAME_NOT_REGISTERED.getCode());
        }
        // 校验是�?�有�?存在的角色
        if (!CollectionUtil.isEmpty(adminAssignRoleDTO.getRoleIds())) {
            List<RoleDO> roles = roleService.getRoles(adminAssignRoleDTO.getRoleIds());
            if (roles.size() != adminAssignRoleDTO.getRoleIds().size()) {
                throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_ASSIGN_ROLE_NOT_EXISTS.getCode());
            }
        }
        // TODO 芋艿，这里先简�?�实现。�?�方�?是，删除�?的分�?的角色关系，然�?�添加新的分�?的角色关系
        // 标记管�?�员角色�?关系都为删除
        adminRoleMapper.deleteByAdminId(adminAssignRoleDTO.getId());
        // 创建 RoleResourceDO 数组，并�?�入到数�?�库
        if (!CollectionUtil.isEmpty(adminAssignRoleDTO.getRoleIds())) {
            List<AdminRoleDO> adminRoleDOs = adminAssignRoleDTO.getRoleIds().stream().map(roleId -> {
                AdminRoleDO roleResource = new AdminRoleDO().setAdminId(adminAssignRoleDTO.getId()).setRoleId(roleId);
                roleResource.setCreateTime(new Date());
                roleResource.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
                return roleResource;
            }).collect(Collectors.toList());
            adminRoleMapper.insertList(adminRoleDOs);
        }
        // TODO �?�入�?作日志
        // 返回�?功
        return true;
    }

    @Override
    public AdminAuthorizationBO checkPermissions(Integer adminId, List<String> permissions) {
        // 查询管�?�员拥有的角色关�?�数�?�
        List<AdminRoleDO> adminRoleList = adminRoleMapper.selectByAdminId(adminId);
        Set<Integer> adminRoleIds = CollectionUtil.convertSet(adminRoleList, AdminRoleDO::getRoleId);
        // 授�?�校验
        if (!CollectionUtil.isEmpty(permissions)) {
            Map<String, List<Integer>> permissionRoleMap = roleService.getPermissionRoleMap(permissions);
            for (Map.Entry<String, List<Integer>> entry : permissionRoleMap.entrySet()) {
                if (!CollectionUtil.containsAny(entry.getValue(), adminRoleIds)) { // 所以有任一�?满足，就验�?失败，抛出异常
                    throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_INVALID_PERMISSION.getCode());
                }
            }
        }
        // 获得用户
        AdminDO admin = adminMapper.selectById(adminId);
        // 返回�?功
        return new AdminAuthorizationBO().setId(adminId).setUsername(admin.getUsername())
                .setRoleIds(adminRoleIds);
    }

    private String encodePassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

}
