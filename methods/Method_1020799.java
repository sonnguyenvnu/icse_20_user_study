/** 
 * ????
 * @param buildConfigDTO
 * @param tableConfig
 * @param zip
 */
public static void gen(BuildConfigDTO buildConfigDTO,TableInfoConfig tableConfig,ZipOutputStream zip){
  Properties prop=new Properties();
  prop.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
  Velocity.init(prop);
  LocalDateTime now=LocalDateTime.now();
  Map<String,Object> map=new HashMap<>();
  map.put("tableInfo",tableConfig);
  map.put("hasBigDecimal",hasBigDecimal);
  map.put("datetime",now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
  map.put("secondModuleName",toLowerCaseFirstOne(tableConfig.getClassName()));
  VelocityContext context=new VelocityContext(map);
  List<String> templates=getTemplates();
  for (  String template : templates) {
    StringWriter sw=new StringWriter();
    Template tpl=Velocity.getTemplate(template,"UTF-8");
    tpl.merge(context,sw);
    try {
      zip.putNextEntry(new ZipEntry(getFileName(template,tableConfig)));
      IOUtils.write(sw.toString(),zip,"UTF-8");
      IOUtils.closeQuietly(sw);
      zip.closeEntry();
    }
 catch (    IOException e) {
      throw new RuntimeException("??????????" + tableConfig.getTableName(),e);
    }
  }
}
