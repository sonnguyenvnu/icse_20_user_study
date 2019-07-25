package com.github.vole.portal.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vole.common.config.db.Query;
import com.github.vole.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class AbstractController<M extends IService<T>, T> implements PortalController<T> {

    @Autowired
    protected M baseService;

    /**
     * 分页查询
     */
    @RequestMapping("/list/{pageNumber}")
    @Override
    public String list(@PathVariable Integer pageNumber, @RequestParam(defaultValue = "15") Integer pageSize, String search, Model model) {
        Page<T> page = getPage(pageNumber, pageSize);
        // 查询分页
        QueryWrapper<T> qw = getSearchQW(search, model);
        Page<T> pageData = (Page<T>) baseService.page(page, qw);
        pageData.getPages();
        model.addAttribute("pageData", pageData);
        return getTemplatePath() + "list";
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    @Override
    public String add(Model model) {
        addModelData(model);
        return getTemplatePath() + "add";
    }



    /**
     * 执行新增
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    @Override
    public R<Boolean> doAdd(T entity) {
        return new R<>(baseService.save(entity));
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    @Override
    public R<Boolean> delete(String id) {
        return new R<>(baseService.removeById(Integer.valueOf(id)));
    }

    /**
     * 批�?删除角色
     */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    @Override
    public R<Boolean> deleteBatch(@RequestParam("id[]") List<String> ids){
        return R.rest(baseService.removeByIds(ids));
    }

    /**
     * 编辑
     */
    @RequestMapping("/edit/{id}")
    @Override
    public String edit(@PathVariable String id, Model model) {
        T entity = baseService.getById(id);
        model.addAttribute("entity", entity);
        addModelData(model);
        return getTemplatePath() + "edit";
    }

    /**
     * 执行编辑
     */
    @RequestMapping("/doEdit")
    @ResponseBody
    @Override
    public R<Boolean> doEdit(T entity, Model model) {
        return new R<>(baseService.updateById(entity));
    }

    /**
     * <p>
     * 获�?�分页对象
     * </p>
     *
     * @param pageSize �?页显示数�?
     * @return
     */
    private <T> Page<T> getPage(int pageNumber, int pageSize) {
        return new Query<T>(pageNumber, pageSize);
    }

    protected String getTemplatePath() {
        return "ftl/";
    }

    protected QueryWrapper<T> getSearchQW(String search, Model model) {
        QueryWrapper<T> ew = new QueryWrapper<T>();
        model.addAttribute("search", search);
        return ew;
    }

    protected void addModelData(Model model) {

    }
}
