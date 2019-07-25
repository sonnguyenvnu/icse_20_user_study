package com.myimooc.excel.boot.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myimooc.excel.boot.domain.model.ImportData;
import com.myimooc.excel.boot.domain.model.ImportDataDetail;
import com.myimooc.excel.boot.domain.vo.Template;
import com.myimooc.excel.boot.service.ImportDataDeatilService;
import com.myimooc.excel.boot.service.ImportDataService;
import com.myimooc.excel.boot.util.CreateTemplateUtils;
import com.myimooc.excel.boot.util.DateUtils;

/**
 * 数�?�导入相关 rest 接�?�
 *
 * @author ZhangCheng on 2017-07-08
 */
@RestController
public class ImportDataController {

    private static Logger logger = LoggerFactory.getLogger(ImportDataController.class);

    @Autowired
    private ImportDataService importDataService;

    @Autowired
    private ImportDataDeatilService importDataDeatilService;

    /**
     * 功能：获�?�导入列表数�?�
     */
    @RequestMapping("importdata-list")
    public Object importdataList() {
        Map<String, Object> respData = new HashMap<String, Object>(1024);
        List<ImportData> importDataList = importDataService.list();
        respData.put("total", importDataList.size());
        respData.put("rows", importDataList);
        return importDataList;
    }

    /**
     * 功能：获�?�导入数�?�模版
     */
    @RequestMapping("importdata-templates")
    public Object importdataTemplates() {
        List<Template> list = new ArrayList<Template>();
        Template t = new Template();
        t.setTemplateId("student");
        t.setTemplateName("student");
        list.add(t);
        return list;
    }

    /**
     * 功能：数�?�导入
     */
    @SuppressWarnings("deprecation")
    @PostMapping("importdata-upload")
    public Object importdataUpload(MultipartFile file) {

        if (null == file) {
            return "上传失败，文件为空";
        }

        try {
            String fileName = file.getName();
            String filePath = getClass().getClassLoader().getResource("config/excel/").getPath() + fileName;
            File excelFile = new File(filePath);
            FileUtils.writeByteArrayToFile(excelFile, file.getBytes());

            ImportData importData = new ImportData();
            Long importDataId = DateUtils.getTimeInstant();
            importData.setId(importDataId);
            importData.setImportDataType("student");
            importData.setImportStatus(1 + "");
            importData.setImportDate(DateUtils.nowToString());
            importDataService.save(importData);

            // 创建工作簿
            HSSFWorkbook workBook = new HSSFWorkbook(file.getInputStream());
            // 读�?�sheet页
            HSSFSheet sheet = workBook.getSheetAt(0);

            // 读�?�工作表中的数�?�
            int firstRowNum = 1;
            int lastRowNum = sheet.getLastRowNum();
            List<ImportDataDetail> importDataDetailList = new ArrayList<ImportDataDetail>();
            for (int i = firstRowNum; i < lastRowNum; i++) {
                // 循环读�?��?一行数�?�
                HSSFRow row = sheet.getRow(i);
                // 获�?�当�?行最�?��?�元格列�?�
                int lastCellNum = row.getLastCellNum();
                ImportDataDetail importDataDetail = new ImportDataDetail();
                for (int j = 0; j < lastCellNum; j++) {
                    // 循环读�?�当�?行中的�?一个�?�元格
                    HSSFCell cell = row.getCell(j);

                    String value;

                    if (CellType.NUMERIC == cell.getCellTypeEnum()) {
                        value = cell.getNumericCellValue() + "";
                        System.out.print(value + "\t");
                    } else {
                        value = cell.getStringCellValue();
                        System.out.print(value + "\t");
                    }
                    System.out.print(value + "\t");

                    switch (j) {
                        case 0:
                            importDataDetail.setCol0(value);
                            break;
                        case 1:
                            importDataDetail.setCol1(value);
                            break;
                        case 2:
                            importDataDetail.setCol2(value);
                            break;
                        case 3:
                            importDataDetail.setCol3(value);
                            break;
                        case 4:
                            importDataDetail.setCol4(value);
                            break;
                        case 5:
                            importDataDetail.setCol5(value);
                            break;
                        case 6:
                            importDataDetail.setCol6(value);
                        default:
                            break;
                    }
                    importDataDetail.setDealStatus(1 + "");
                    importDataDetail.setImportId(importDataId);
                    importDataDetail.setId(DateUtils.getTimeInstant() + Math.round(DateUtils.getTimeInstant()));
                }
                System.out.println();
                importDataDetailList.add(importDataDetail);

            }
            // 释放资�?
            workBook.close();

            importDataDeatilService.save(importDataDetailList);

            importData = importDataService.findOne(importDataId);
            importData.setDealDate(DateUtils.nowToString());
            importData.setDealStatus(1 + "");
            importDataService.save(importData);

            return "上传�?功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    /**
     * 功能：下载导入数�?�模版
     */
    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response, String templateId) {
        String fileName = "student.xls";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        String xmlPath = getClass().getClassLoader().getResource("config/excel/student.xml").getPath();
        String filePath = getClass().getClassLoader().getResource("config/excel/").getPath();

        File xmlFile = new File(xmlPath);
        File excelFile = new File(filePath + fileName);
        try {
            CreateTemplateUtils.create(xmlFile, excelFile);
            logger.info("创建�?功：{}", excelFile.getName());
            InputStream in = FileUtils.openInputStream(excelFile);
            int b;
            while ((b = in.read()) != -1) {
                response.getOutputStream().write(b);
            }
        } catch (Exception e) {
            logger.info("创建失败，异常为：{}", e);
            e.printStackTrace();
        }
    }
}
