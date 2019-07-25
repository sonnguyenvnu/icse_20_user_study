package com.fisher.gen.util;

import com.fisher.gen.model.config.ColumnInfoConfig;
import com.fisher.gen.model.config.TableInfoConfig;
import com.fisher.gen.model.dto.BuildConfigDTO;
import com.fisher.gen.model.entity.ColumnInfo;
import com.fisher.gen.model.entity.TableInfo;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.BeanUtils;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class GenUtil {

    private static Boolean hasBigDecimal = false;

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        {
            templates.add("templates/Entity.java.vm");
            templates.add("templates/Mapper.xml.vm");
            templates.add("templates/Mapper.java.vm");
            templates.add("templates/service.java.vm");
            templates.add("templates/serviceImpl.java.vm");
            templates.add("templates/query.java.vm");
            templates.add("templates/controller.java.vm");
            templates.add("templates/index.js.vm");
            templates.add("templates/index.vue.vm");
        }
        return templates;
    }

    /**
     * ç”Ÿæˆ?ä»£ç ?
     * @param tableInfo 
     * @param columns
     * @param zip
     */
    public static void generatorCode(BuildConfigDTO buildConfigDTO, TableInfo tableInfo, List<ColumnInfo> columns,
                                     ZipOutputStream zip) {
        hasBigDecimal = false;
        // é…?ç½®ä¿¡æ?¯
        Configuration config = getConfig();
        // è¡¨ä¿¡æ?¯
        TableInfoConfig tableConfig = new TableInfoConfig();

        //  é…?ç½® åŒ…å??ç­‰é…?ç½®
        buildTableConfig(buildConfigDTO ,config, tableConfig);


        BeanUtils.copyProperties(tableInfo, tableConfig);

        //  æž„å»ºè¡¨åŸºæœ¬ä¿¡æ?¯
        buildTableInfo(config, tableConfig,columns);

        // ç”Ÿæˆ?ä»£ç ?
        gen(buildConfigDTO, tableConfig, zip);



    }

    /**
     * åˆ—å??è½¬æ?¢æˆ?Javaå±žæ€§å??
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
    }

    /**
     * è¡¨å??è½¬æ?¢æˆ?Javaç±»å??
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * èŽ·å?–é…?ç½®ä¿¡æ?¯
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("èŽ·å?–é…?ç½®æ–‡ä»¶å¤±è´¥ï¼Œ", e);
        }
    }

    /**
     * èŽ·å?–æ–‡ä»¶å??
     */
    public static String getFileName(String template,TableInfoConfig tableInfoConfig) {
        String packagePath = "main" + File.separator + "java" + File.separator
                + tableInfoConfig.getPackageName().replace(".", File.separator);
        String frontPath = "ui" + File.separator;
        String resourcesPath = "main" + File.separator + "resources";
        String className = tableInfoConfig.getClassName();
        if (template.contains("templates/index.js.vm")) {
            return frontPath + "api" + File.separator + tableInfoConfig.getClassName() + File.separator + toLowerCaseFirstOne(className) + File.separator + "index.js";
        }

        if (template.contains("templates/index.vue.vm")) {
            return frontPath + "views" + File.separator + tableInfoConfig.getClassName() + File.separator + toLowerCaseFirstOne(className) + File.separator + "index.vue";
        }

        if (template.contains("templates/Entity.java.vm")) {
            return packagePath + File.separator + className
                    + ".java";
        }
        if (template.contains("templates/query.java.vm")) {
            String path = "main" + File.separator + "java" + File.separator
                    + tableInfoConfig.getQueryPackageName().replace(".", File.separator);
            return path + File.separator + className
                    + "Query.java";
        }
        if (template.contains("templates/Mapper.java.vm")) {
            String path = "main" + File.separator + "java" + File.separator
                    + tableInfoConfig.getMapperPackageName().replace(".", File.separator);
            return path + File.separator + className
                    + "Mapper.java";
        }
        if (template.contains("templates/service.java.vm")) {
            String path = "main" + File.separator + "java" + File.separator
                    + tableInfoConfig.getServicePackageName().replace(".", File.separator);
            return path + File.separator + className
                    + "Service.java";
        }
        if (template.contains("templates/serviceImpl.java.vm")) {
            String path = "main" + File.separator + "java" + File.separator
                    + tableInfoConfig.getServicePackageName().replace(".", File.separator);
            return path + File.separator + "impl" + File.separator + className
                    + "ServiceImpl.java";
        }
        if (template.contains("templates/controller.java.vm")) {
            String path = "main" + File.separator + "java" + File.separator
                    + tableInfoConfig.getControllerPackageName().replace(".", File.separator);
            return path + File.separator + className
                    + "Controller.java";
        }
        if (template.contains("templates/Mapper.xml.vm")) {
            return resourcesPath + File.separator+  "mapper" + File.separator+ className
                    + "Mapper.xml";
        }
        return null;
    }

    /**
     * æž„å»ºè¡¨é…?ç½®ä¿¡æ?¯  åŒ…å??ç­‰
     * @param buildConfigDTO
     * @param configuration
     * @param tableInfoConfig
     */
    public static void buildTableConfig(BuildConfigDTO buildConfigDTO,Configuration configuration,TableInfoConfig tableInfoConfig) {

        BeanUtils.copyProperties(buildConfigDTO, tableInfoConfig);

        if(StringUtils.isEmpty(tableInfoConfig.getPackageName())) {
            tableInfoConfig.setPackageName(configuration.getString("packageName", "com.fisher.gen.model.entity"));

        }
        if(StringUtils.isEmpty(tableInfoConfig.getDaoPackageName())) {
            tableInfoConfig.setDaoPackageName(configuration.getString("daoPackageName", "com.fisher.gen.mapper"));

        }
        if(StringUtils.isEmpty(tableInfoConfig.getControllerPackageName())) {
            tableInfoConfig.setDaoPackageName(configuration.getString("controllerPackageName", "com.fisher.gen.controller"));

        }
        if(StringUtils.isEmpty(tableInfoConfig.getMapperPackageName())) {
            tableInfoConfig.setDaoPackageName(configuration.getString("mapperPackageName", "com.fisher.gen.mapper"));

        }

        if(StringUtils.isEmpty(tableInfoConfig.getAuthorName())) {
            tableInfoConfig.setAuthorName(configuration.getString("authorName", "Allen"));
        }

        if(StringUtils.isEmpty(tableInfoConfig.getServiceApiPackageName())) {
            tableInfoConfig.setServiceApiPackageName(configuration.getString("serviceApiPackageName", "com.fisher.gen.service.api"));
        }

        if(StringUtils.isEmpty(tableInfoConfig.getServicePackageName())) {
            tableInfoConfig.setServicePackageName(configuration.getString("servicePackageName", "com.fisher.gen.service"));
        }

        if(StringUtils.isEmpty(tableInfoConfig.getQueryPackageName())) {
            tableInfoConfig.setQueryPackageName(configuration.getString("queryPackageName", "com.fisher.gen.mapper"));
        }

    }

    /**
     * æž„å»ºè¡¨åŸºæœ¬æ•°æ?®ä¿¡æ?¯
     * @param config
     * @param tableConfig
     * @param columns
     */
    public static void  buildTableInfo(Configuration config,TableInfoConfig tableConfig,List<ColumnInfo> columns) {
        // è¡¨å??è½¬æ?¢æˆ?Javaç±»å??
        String className = tableToJava(tableConfig.getTableName(), config.getString("tablePrefix"));
        tableConfig.setClassName(className);
        tableConfig.setLowerClassName(StringUtils.uncapitalize(className));

        // åˆ—ä¿¡æ?¯
        List<ColumnInfoConfig> columnInfoConfigs = new ArrayList<>();
        for (ColumnInfo column : columns) {


            ColumnInfoConfig columnEntity = new ColumnInfoConfig();
            BeanUtils.copyProperties(column, columnEntity);

            // åˆ—å??è½¬æ?¢æˆ?Javaå±žæ€§å??
            String upAttrName = columnToJava(column.getColumnName());
            columnEntity.setUpAttrName(upAttrName);
            String temp = columnEntity.getUpAttrName();
            temp = (new StringBuilder()).append(Character.toLowerCase(temp.charAt(0)))
                    .append(temp.substring(1)).toString();
            columnEntity.setAttrName(temp);
            // åˆ—çš„æ•°æ?®ç±»åž‹ï¼Œè½¬æ?¢æˆ?Javaç±»åž‹
            String attrType = config.getString(columnEntity.getDataType(), "unknowType");
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && StringUtils.equals("BigDecimal", column.getDataType())) {
                hasBigDecimal = true;
            }
            // æ˜¯å?¦ä¸»é”®
            if ( tableConfig.getPk() == null && StringUtils.equalsIgnoreCase("PRI", column.getColumnKey()) ) {
                tableConfig.setPk(columnEntity);
            }

            columnInfoConfigs.add(columnEntity);
        }
        tableConfig.setColumnInfo(columnInfoConfigs);

        // æ²¡ä¸»é”®ï¼Œåˆ™ç¬¬ä¸€ä¸ªå­—æ®µä¸ºä¸»é”®
        if (tableConfig.getPk() == null) {
            tableConfig.setPk(tableConfig.getColumnInfo().get(0));
        }
    }

    /**
     * ç”Ÿæˆ?ä»£ç ?
     * @param buildConfigDTO
     * @param tableConfig
     * @param zip
     */
    public static void gen(BuildConfigDTO buildConfigDTO,TableInfoConfig tableConfig, ZipOutputStream zip) {
        // è®¾ç½®velocityèµ„æº?åŠ è½½å™¨
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        LocalDateTime now = LocalDateTime.now();
        // å°?è£…æ¨¡æ?¿æ•°æ?®
        Map<String, Object> map = new HashMap<>();
        map.put("tableInfo", tableConfig);
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("datetime", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        map.put("secondModuleName", toLowerCaseFirstOne(tableConfig.getClassName()));
        VelocityContext context = new VelocityContext(map);

        // èŽ·å?–æ¨¡æ?¿åˆ—è¡¨
        List<String> templates = getTemplates();
        for (String template : templates) {
            // æ¸²æŸ“æ¨¡æ?¿
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                // æ·»åŠ åˆ°zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableConfig)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException("æ¸²æŸ“æ¨¡æ?¿å¤±è´¥ï¼Œè¡¨å??ï¼š" + tableConfig.getTableName(), e);
            }
        }

    }

    //é¦–å­—æ¯?è½¬å°?å†™
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
