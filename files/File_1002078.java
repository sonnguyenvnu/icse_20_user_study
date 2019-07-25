package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Dict;
import cn.stylefeng.guns.modular.system.entity.DictType;
import cn.stylefeng.guns.modular.system.model.params.DictParam;
import cn.stylefeng.guns.modular.system.model.result.DictResult;
import cn.stylefeng.guns.modular.system.service.DictService;
import cn.stylefeng.guns.modular.system.service.DictTypeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 基础字典控制器
 *
 * @author stylefeng
 * @Date 2019-03-13 13:53:53
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    private String PREFIX = "/modular/system/dict";

    @Autowired
    private DictService dictService;

    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 跳转到主页�?�
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping("")
    public String index(@RequestParam("dictTypeId") Long dictTypeId, Model model) {
        model.addAttribute("dictTypeId", dictTypeId);

        //获�?�type的�??称
        DictType dictType = dictTypeService.getById(dictTypeId);
        if (dictType == null) {
            throw new RequestEmptyException();
        }
        model.addAttribute("dictTypeName", dictType.getName());

        return PREFIX + "/dict.html";
    }

    /**
     * 新增页�?�
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping("/add")
    public String add(@RequestParam("dictTypeId") Long dictTypeId, Model model) {
        model.addAttribute("dictTypeId", dictTypeId);

        //获�?�type的�??称
        DictType dictType = dictTypeService.getById(dictTypeId);
        if (dictType == null) {
            throw new RequestEmptyException();
        }

        model.addAttribute("dictTypeName", dictType.getName());
        return PREFIX + "/dict_add.html";
    }

    /**
     * 编辑页�?�
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam("dictId") Long dictId, Model model) {

        //获�?�type的id
        Dict dict = dictService.getById(dictId);
        if (dict == null) {
            throw new RequestEmptyException();
        }

        //获�?�type的�??称
        DictType dictType = dictTypeService.getById(dict.getDictTypeId());
        if (dictType == null) {
            throw new RequestEmptyException();
        }

        model.addAttribute("dictTypeId", dict.getDictTypeId());
        model.addAttribute("dictTypeName", dictType.getName());

        return PREFIX + "/dict_edit.html";
    }

    /**
     * 新增接�?�
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DictParam dictParam) {
        this.dictService.add(dictParam);
        return ResponseData.success();
    }

    /**
     * 编辑接�?�
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(DictParam dictParam) {
        this.dictService.update(dictParam);
        return ResponseData.success();
    }

    /**
     * 删除接�?�
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(DictParam dictParam) {
        this.dictService.delete(dictParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接�?�
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(DictParam dictParam) {
        DictResult dictResult = this.dictService.dictDetail(dictParam.getDictId());
        return ResponseData.success(dictResult);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DictParam dictParam) {
        return this.dictService.findPageBySpec(dictParam);
    }

    /**
     * 获�?��?个类型下字典树的列表，ztree格�?
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping(value = "/ztree")
    @ResponseBody
    public List<ZTreeNode> ztree(@RequestParam("dictTypeId") Long dictTypeId, @RequestParam(value = "dictId", required = false) Long dictId) {
        return this.dictService.dictTreeList(dictTypeId, dictId);
    }

}


