package com.kakarote.crm9.erp.crm.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.kakarote.crm9.common.annotation.NotNullValidate;
import com.kakarote.crm9.common.annotation.Permissions;
import com.kakarote.crm9.erp.admin.service.AdminFieldService;
import com.kakarote.crm9.erp.admin.service.AdminSceneService;
import com.kakarote.crm9.erp.crm.entity.CrmProduct;
import com.kakarote.crm9.erp.crm.service.CrmProductService;
import com.kakarote.crm9.common.config.paragetter.BasePageRequest;
import com.kakarote.crm9.utils.R;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrmProductController extends Controller {

    @Inject
    private CrmProductService crmProductService;

    @Inject
    private AdminFieldService adminFieldService;

    @Inject
    private AdminSceneService adminSceneService;

    /**
     * @author wyq
     * 查看列表页
     */
    @Permissions({"crm:product:index"})
    public void queryPageList(BasePageRequest basePageRequest){
        JSONObject jsonObject = basePageRequest.getJsonObject().fluentPut("type",4);
        basePageRequest.setJsonObject(jsonObject);
        renderJson(adminSceneService.filterConditionAndGetPageList(basePageRequest));
    }

    /**
     * 分页�?�件查询产�?
     *
     * @author zxy
     */
    public void queryList(BasePageRequest<CrmProduct> basePageRequest) {
        renderJson(R.ok().put("data", crmProductService.queryPage(basePageRequest)));
    }

    /**
     * 添加或修改产�?
     *
     * @author zxy
     */
    @Permissions({"crm:product:save","crm:product:update"})
    public void saveAndUpdate() {
        String data = getRawData();
        JSONObject jsonObject = JSON.parseObject(data);
        renderJson(crmProductService.saveAndUpdate(jsonObject));
    }

    /**
     * 根�?�id查询产�?
     *
     * @author zxy
     */
    @Permissions("crm:product:read")
    @NotNullValidate(value = "productId", message = "产�?id�?能为空")
    public void queryById(@Para("productId") Integer productId) {
        renderJson(crmProductService.queryById(productId));
    }

    /**
     * 根�?�id查删除产�?
     *
     * @author zxy
     */
    @Permissions("crm:product:delete")
    @NotNullValidate(value = "productId", message = "产�?id�?能为空")
    public void deleteById(@Para("productId") Integer productId) {
        renderJson(crmProductService.deleteById(productId));
    }

    /**
     * 产�?上下架 status 0:下架 1：上架（默认除了0之外其他都是上架）
     *
     * @author zxy
     */
    @Permissions("crm:product:status")
    public void updateStatus(@Para("ids") String ids, @Para("status") Integer status) {
        if (status == null)
        { status = 1;}
        renderJson(crmProductService.updateStatus(ids, status));
    }

    /**
     * 查询产�?自定义字段
     *
     * @author zxy
     */
    public void queryField() {
        renderJson(R.ok().put("data", crmProductService.queryField()));
    }

    /**
     * @author wyq
     * 批�?导出产�?
     */
    @Permissions("crm:product:excelexport")
    public void batchExportExcel(@Para("ids")String productIds) throws IOException {
        List<Record> recordList = crmProductService.exportProduct(productIds);
        export(recordList);
        renderNull();
    }

    /**
     * @author wyq
     * 导出全部产�?
     */
    @Permissions("crm:product:excelexport")
    public void allExportExcel(BasePageRequest basePageRequest) throws IOException{
        JSONObject jsonObject = basePageRequest.getJsonObject();
        jsonObject.fluentPut("excel","yes").fluentPut("type","4");
        AdminSceneService adminSceneService = new AdminSceneService();
        List<Record> recordList = (List<Record>)adminSceneService.filterConditionAndGetPageList(basePageRequest).get("data");
        export(recordList);
        renderNull();
    }

    private void export(List<Record> recordList) throws IOException{
        ExcelWriter writer = ExcelUtil.getWriter();
        AdminFieldService adminFieldService = new AdminFieldService();
        List<Record> fieldList = adminFieldService.customFieldList("4");
        writer.addHeaderAlias("name","产�?�??称");
        writer.addHeaderAlias("num","产�?编�?");
        writer.addHeaderAlias("category_name","产�?类别");
        writer.addHeaderAlias("price","价格");
        writer.addHeaderAlias("description","产�?�??述");
        writer.addHeaderAlias("create_user_name","创建人");
        writer.addHeaderAlias("owner_user_name","负责人");
        writer.addHeaderAlias("create_time","创建时间");
        writer.addHeaderAlias("update_time","更新时间");
        for (Record field:fieldList){
            writer.addHeaderAlias(field.getStr("name"),field.getStr("name"));
        }
        writer.merge(8+fieldList.size(),"产�?信�?�");
        HttpServletResponse response = getResponse();
        List<Map<String,Object>> list = new ArrayList<>();
        for (Record record : recordList){
            list.add(record.remove("batch_id","status","unit","category_id","product_id","owner_user_id","create_user_id","field_batch_id").getColumns());
        }
        writer.write(list,true);
        for (int i=0; i < fieldList.size()+15;i++){
            writer.setColumnWidth(i,20);
        }
        //自定义标题别�??
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        //test.xls是弹出下载对�?框的文件�??，�?能为中文，中文请自行编�?
        response.setHeader("Content-Disposition", "attachment;filename=product.xls");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out);
        // 关闭writer，释放内存
        writer.close();
    }

    /**
     * @author zxy
     * 获�?�导入模�?�
     */
    public void downloadExcel(){
        List<Record> recordList = adminFieldService.queryAddField(4);
        recordList.removeIf(record -> "file".equals(record.getStr("formType")) || "checkbox".equals(record.getStr("formType"))|| "user".equals(record.getStr("formType"))|| "structure".equals(record.getStr("formType")));
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("产�?导入表");
        HSSFRow row = sheet.createRow(0);
        List<String> categoryList = Db.query("select name from 72crm_crm_product_category");
        for (int i=0;i < recordList.size();i++){
            Record record = recordList.get(i);
            String[] setting = record.get("setting");
            HSSFCell cell = row.createCell(i);
            if (record.getInt("is_null") == 1){
                cell.setCellValue(record.getStr("name")+"(*)");
            }else {
                cell.setCellValue(record.getStr("name"));
            }
            if ("产�?类型".equals(record.getStr("name"))){
                setting = categoryList.toArray(new String[categoryList.size()]);
            }
            if (setting.length != 0){
                CellRangeAddressList regions = new CellRangeAddressList(0, Integer.MAX_VALUE, i, i);
                DVConstraint constraint = DVConstraint.createExplicitListConstraint(setting);
                HSSFDataValidation dataValidation = new HSSFDataValidation(regions,constraint);
                sheet.addValidationData(dataValidation);
            }
        }
        HttpServletResponse response = getResponse();
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            //test.xls是弹出下载对�?框的文件�??，�?能为中文，中文请自行编�?
            response.setHeader("Content-Disposition", "attachment;filename=product_import.xls");
            wb.write(response.getOutputStream());
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        renderNull();
    }

    /**
     * @author zxy
     * 导入产�?
     */
    @Permissions("crm:product:excelimport")
    public void uploadExcel (@Para("file") UploadFile file, @Para("repeatHandling") Integer repeatHandling, @Para("ownerUserId") Integer ownerUserId){
        Db.tx(() ->{
            R result = crmProductService.uploadExcel(file,repeatHandling,ownerUserId);
            renderJson(result);
            if (result.get("code").equals(500)){
                return false;
            }
            return true;
        });
    }

    /**
     * @author zxy
     * 获�?�上架商�?
     */
    public void queryByStatus(BasePageRequest<CrmProduct> basePageRequest){
        renderJson(crmProductService.queryByStatus(basePageRequest));
    }

}
