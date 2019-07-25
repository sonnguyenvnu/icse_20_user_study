package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.bo.resource.ResourceBO;
import cn.iocoder.mall.admin.api.constant.ResourceConstants;
import cn.iocoder.mall.admin.api.dto.resource.ResourceAddDTO;
import cn.iocoder.mall.admin.api.dto.resource.ResourceUpdateDTO;
import cn.iocoder.mall.admin.application.convert.ResourceConvert;
import cn.iocoder.mall.admin.application.vo.resource.ResourceTreeNodeVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/resource")
@Api("资�?模�?�")
public class ResourceController {

    @Reference(validation = "true", version = "${dubbo.provider.ResourceService.version}")
    private ResourceService resourceService;

    @SuppressWarnings("Duplicates")
    @GetMapping("/tree")
    @ApiOperation(value = "获得所有资�?，按照树形结构返回")
    public CommonResult<List<ResourceTreeNodeVO>> tree() {
        List<ResourceBO> resources = resourceService.getResourcesByType(null);
        // 创建 AdminMenuTreeNodeVO Map
        Map<Integer, ResourceTreeNodeVO> treeNodeMap = resources.stream().collect(Collectors.toMap(ResourceBO::getId, ResourceConvert.INSTANCE::convert2));
        // 处�?�父�?关系
        treeNodeMap.values().stream()
                .filter(node -> !node.getPid().equals(ResourceConstants.PID_ROOT))
                .forEach((childNode) -> {
                    // 获得父节点
                    ResourceTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
                    if (parentNode.getChildren() == null) { // �?始化 children 数组
                        parentNode.setChildren(new ArrayList<>());
                    }
                    // 将自己添加到父节点中
                    parentNode.getChildren().add(childNode);
                });
        // 获得到所有的根节点
        List<ResourceTreeNodeVO> rootNodes = treeNodeMap.values().stream()
                .filter(node -> node.getPid().equals(ResourceConstants.PID_ROOT))
                .sorted(Comparator.comparing(ResourceTreeNodeVO::getSort))
                .collect(Collectors.toList());
        return success(rootNodes);
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建资�?", notes = "例如说，�?��?�资�?，Url 资�?")
    public CommonResult<ResourceBO> add(ResourceAddDTO resourceAddDTO) {
        return success(resourceService.addResource(AdminSecurityContextHolder.getContext().getAdminId(), resourceAddDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新资�?")
    public CommonResult<Boolean> update(ResourceUpdateDTO resourceUpdateDTO) {
        return success(resourceService.updateResource(AdminSecurityContextHolder.getContext().getAdminId(), resourceUpdateDTO));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除资�?")
    @ApiImplicitParam(name = "id", value = "资�?编�?�", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return success(resourceService.deleteResource(AdminSecurityContextHolder.getContext().getAdminId(), id));
    }

}
