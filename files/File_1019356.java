package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.DataDictService;
import cn.iocoder.mall.admin.api.bo.datadict.DataDictBO;
import cn.iocoder.mall.admin.api.dto.datadict.DataDictAddDTO;
import cn.iocoder.mall.admin.api.dto.datadict.DataDictUpdateDTO;
import cn.iocoder.mall.admin.application.convert.DataDictConvert;
import cn.iocoder.mall.admin.application.vo.datadict.DataDictEnumVO;
import cn.iocoder.mall.admin.sdk.annotation.RequiresPermissions;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/data_dict")
@Api("数�?�字典模�?�")
public class DataDictController {

    @Reference(validation = "true", version = "${dubbo.provider.DataDictService.version}")
    private DataDictService dataDictService;

    @GetMapping("/list")
    @ApiOperation(value = "数�?�字典全列表")
    @RequiresPermissions("system.dataDict.list")
    public CommonResult<List<DataDictBO>> list() {
        return success( dataDictService.selectDataDictList());
    }

    @GetMapping("/tree")
    @RequiresPermissions({}) // 因为是通用的接�?�，所以无需�?��?标识
    @ApiOperation(value = "数�?�字典树结构", notes = "该接�?�返回的信�?�更为精简。一般用于�?端缓存数�?�字典到本地。")
    public CommonResult<List<DataDictEnumVO>> tree() {
        // 查询数�?�字典全列表
        List<DataDictBO> dataDicts = dataDictService.selectDataDictList();
        // 构建基于 enumValue �?��?�的 Multimap
        ImmutableListMultimap<String, DataDictBO> dataDictMap = Multimaps.index(dataDicts, DataDictBO::getEnumValue); // KEY 是 enumValue ，VALUE 是 DataDictBO 数组
        // 构建返回结果
        List<DataDictEnumVO> dataDictEnumVOs = new ArrayList<>(dataDictMap.size());
        dataDictMap.keys().forEach(enumValue -> {
            DataDictEnumVO dataDictEnumVO = new DataDictEnumVO().setEnumValue(enumValue)
                    .setValues(DataDictConvert.INSTANCE.convert2(dataDictMap.get(enumValue)));
            dataDictEnumVOs.add(dataDictEnumVO);
        });
        return success(dataDictEnumVOs);
    }

    @PostMapping("/add")
    @RequiresPermissions("system.dataDict.add")
    @ApiOperation(value = "创建数�?�字典")
    public CommonResult<DataDictBO> add(DataDictAddDTO dataDictAddDTO) {
        return success(dataDictService.addDataDict(AdminSecurityContextHolder.getContext().getAdminId(), dataDictAddDTO));
    }

    @PostMapping("/update")
    @RequiresPermissions("system.dataDict.update")
    @ApiOperation(value = "更新数�?�字典")
    public CommonResult<Boolean> update(DataDictUpdateDTO dataDictUpdateDTO) {
        return success(dataDictService.updateDataDict(AdminSecurityContextHolder.getContext().getAdminId(), dataDictUpdateDTO));
    }

    @PostMapping("/delete")
    @RequiresPermissions("system.dataDict.delete")
    @ApiOperation(value = "删除数�?�字典")
    @ApiImplicitParam(name = "id", value = "编�?�", required = true, example = "100")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return success(dataDictService.deleteDataDict(AdminSecurityContextHolder.getContext().getAdminId(), id));
    }

}
