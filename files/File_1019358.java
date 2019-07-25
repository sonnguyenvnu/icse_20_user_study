package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.RoleService;
import cn.iocoder.mall.admin.api.bo.resource.ResourceBO;
import cn.iocoder.mall.admin.api.bo.role.RoleBO;
import cn.iocoder.mall.admin.api.constant.ResourceConstants;
import cn.iocoder.mall.admin.api.dto.role.RoleAddDTO;
import cn.iocoder.mall.admin.api.dto.role.RoleAssignResourceDTO;
import cn.iocoder.mall.admin.api.dto.role.RolePageDTO;
import cn.iocoder.mall.admin.api.dto.role.RoleUpdateDTO;
import cn.iocoder.mall.admin.application.convert.ResourceConvert;
import cn.iocoder.mall.admin.application.vo.role.RoleResourceTreeNodeVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/role")
@Api("角色模�?�")
public class RoleController {

    @Reference(validation = "true", version = "${dubbo.provider.RoleService.version}")
    private RoleService roleService;

    @Reference(validation = "true", version = "${dubbo.provider.ResourceService.version}")
    private ResourceService resourceService;

    @GetMapping("/page")
    @ApiOperation(value = "角色分页")
    public CommonResult<PageResult<RoleBO>> page(RolePageDTO rolePageDTO) {
        return success(roleService.getRolePage(rolePageDTO));
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建角色")
    public CommonResult<RoleBO> add(RoleAddDTO roleAddDTO) {
        return success(roleService.addRole(AdminSecurityContextHolder.getContext().getAdminId(), roleAddDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新角色")
    public CommonResult<Boolean> update(RoleUpdateDTO roleUpdateDTO) {
        return success(roleService.updateRole(AdminSecurityContextHolder.getContext().getAdminId(), roleUpdateDTO));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "id", value = "角色编�?�", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return success(roleService.deleteRole(AdminSecurityContextHolder.getContext().getAdminId(), id));
    }

    @SuppressWarnings("Duplicates")
    @GetMapping("/resource_tree")
    @ApiOperation(value = "获得角色拥有的�?��?��?��?", notes = "以树结构返回")
    @ApiImplicitParam(name = "id", value = "角色编�?�", required = true, example = "1")
    public CommonResult<List<RoleResourceTreeNodeVO>> resourceTree(@RequestParam("id") Integer id) {
        // 芋艿：此处，严格�?�说�?�以在校验下角色是�?�存在。�?过呢，校验了也没啥�?义，因为一般�?存在这个情况，且�?会有业务上的影�?。并且，�??倒多了一次 rpc 调用。
        // 第一步，获得角色拥有的资�?数组
        Set<Integer> roleResources = resourceService.getResourcesByTypeAndRoleIds(null, CollectionUtil.asSet(id))
                .stream().map(ResourceBO::getId).collect(Collectors.toSet());
        // 第二步，获得资�?树
        List<ResourceBO> allResources = resourceService.getResourcesByType(null);
        // 创建 AdminMenuTreeNodeVO Map
        Map<Integer, RoleResourceTreeNodeVO> treeNodeMap = allResources.stream().collect(Collectors.toMap(ResourceBO::getId, ResourceConvert.INSTANCE::convert4));
        // 处�?�父�?关系
        treeNodeMap.values().stream()
                .filter(node -> !node.getPid().equals(ResourceConstants.PID_ROOT))
                .forEach((childNode) -> {
                    // 获得父节点
                    RoleResourceTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
                    if (parentNode.getChildren() == null) { // �?始化 children 数组
                        parentNode.setChildren(new ArrayList<>());
                    }
                    // 将自己添加到父节点中
                    parentNode.getChildren().add(childNode);
                });
        // 获得到所有的根节点
        List<RoleResourceTreeNodeVO> rootNodes = treeNodeMap.values().stream()
                .filter(node -> node.getPid().equals(ResourceConstants.PID_ROOT))
                .sorted(Comparator.comparing(RoleResourceTreeNodeVO::getSort))
                .collect(Collectors.toList());
        // 第三步，设置角色是�?�有该角色
        treeNodeMap.values().forEach(nodeVO -> nodeVO.setAssigned(roleResources.contains(nodeVO.getId())));
        // 返回结果
        return success(rootNodes);
    }

    @PostMapping("/assign_resource")
    @ApiOperation(value = "分�?角色资�?")
    public CommonResult<Boolean> assignResource(RoleAssignResourceDTO roleAssignResourceDTO) {
        return success(roleService.assignRoleResource(AdminSecurityContextHolder.getContext().getAdminId(), roleAssignResourceDTO));
    }

}
