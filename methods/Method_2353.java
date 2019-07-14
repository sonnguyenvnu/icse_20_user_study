/** 
 * ??????generatorConfig.xml??
 * @param jdbcDriver   ????
 * @param jdbcUrl      ??
 * @param jdbcUsername ??
 * @param jdbcPassword ??
 * @param module        ????
 * @param database      ???
 * @param tablePrefix  ???
 * @param packageName  ??
 */
public static void generator(String jdbcDriver,String jdbcUrl,String jdbcUsername,String jdbcPassword,String module,String database,String tablePrefix,String packageName,Map<String,String> lastInsertIdTables) throws Exception {
  String os=System.getProperty("os.name");
  String targetProject=module + "/" + module + "-dao";
  String basePath=MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/","").replace(targetProject,"");
  if (os.toLowerCase().startsWith("win")) {
    generatorConfig_vm=MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/","");
    service_vm=MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/","");
    serviceMock_vm=MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath().replaceFirst("/","");
    serviceImpl_vm=MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/","");
    basePath=basePath.replaceFirst("/","");
  }
 else {
    generatorConfig_vm=MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
    service_vm=MybatisGeneratorUtil.class.getResource(service_vm).getPath();
    serviceMock_vm=MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath();
    serviceImpl_vm=MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath();
  }
  String generatorConfigXml=MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/","") + "/src/main/resources/generatorConfig.xml";
  targetProject=basePath + targetProject;
  String sql="SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name LIKE '" + tablePrefix + "_%';";
  System.out.println("========== ????generatorConfig.xml?? ==========");
  List<Map<String,Object>> tables=new ArrayList<>();
  try {
    VelocityContext context=new VelocityContext();
    Map<String,Object> table;
    JdbcUtil jdbcUtil=new JdbcUtil(jdbcDriver,jdbcUrl,jdbcUsername,AESUtil.aesDecode(jdbcPassword));
    List<Map> result=jdbcUtil.selectByParams(sql,null);
    for (    Map map : result) {
      System.out.println(map.get("TABLE_NAME"));
      table=new HashMap<>(2);
      table.put("table_name",map.get("TABLE_NAME"));
      table.put("model_name",lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
      tables.add(table);
    }
    jdbcUtil.release();
    String targetProjectSqlMap=basePath + module + "/" + module + "-rpc-service";
    context.put("tables",tables);
    context.put("generator_javaModelGenerator_targetPackage",packageName + ".dao.model");
    context.put("generator_sqlMapGenerator_targetPackage",packageName + ".dao.mapper");
    context.put("generator_javaClientGenerator_targetPackage",packageName + ".dao.mapper");
    context.put("targetProject",targetProject);
    context.put("targetProject_sqlMap",targetProjectSqlMap);
    context.put("generator_jdbc_password",AESUtil.aesDecode(jdbcPassword));
    context.put("last_insert_id_tables",lastInsertIdTables);
    VelocityUtil.generate(generatorConfig_vm,generatorConfigXml,context);
    deleteDir(new File(targetProject + "/src/main/java/" + packageName.replaceAll("\\.","/") + "/dao/model"));
    deleteDir(new File(targetProject + "/src/main/java/" + packageName.replaceAll("\\.","/") + "/dao/mapper"));
    deleteDir(new File(targetProjectSqlMap + "/src/main/java/" + packageName.replaceAll("\\.","/") + "/dao/mapper"));
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  System.out.println("========== ????generatorConfig.xml?? ==========");
  System.out.println("========== ????MybatisGenerator ==========");
  List<String> warnings=new ArrayList<>();
  File configFile=new File(generatorConfigXml);
  ConfigurationParser cp=new ConfigurationParser(warnings);
  Configuration config=cp.parseConfiguration(configFile);
  DefaultShellCallback callback=new DefaultShellCallback(true);
  MyBatisGenerator myBatisGenerator=new MyBatisGenerator(config,callback,warnings);
  myBatisGenerator.generate(null);
  for (  String warning : warnings) {
    System.out.println(warning);
  }
  System.out.println("========== ????MybatisGenerator ==========");
  System.out.println("========== ????Service ==========");
  String ctime=new SimpleDateFormat("yyyy/M/d").format(new Date());
  String servicePath=basePath + module + "/" + module + "-rpc-api" + "/src/main/java/" + packageName.replaceAll("\\.","/") + "/rpc/api";
  String serviceImplPath=basePath + module + "/" + module + "-rpc-service" + "/src/main/java/" + packageName.replaceAll("\\.","/") + "/rpc/service/impl";
  for (int i=0; i < tables.size(); i++) {
    String model=StringUtil.lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")));
    String service=servicePath + "/" + model + "Service.java";
    String serviceMock=servicePath + "/" + model + "ServiceMock.java";
    String serviceImpl=serviceImplPath + "/" + model + "ServiceImpl.java";
    File serviceFile=new File(service);
    if (!serviceFile.exists()) {
      VelocityContext context=new VelocityContext();
      context.put("package_name",packageName);
      context.put("model",model);
      context.put("ctime",ctime);
      VelocityUtil.generate(service_vm,service,context);
      System.out.println(service);
    }
    File serviceMockFile=new File(serviceMock);
    if (!serviceMockFile.exists()) {
      VelocityContext context=new VelocityContext();
      context.put("package_name",packageName);
      context.put("model",model);
      context.put("ctime",ctime);
      VelocityUtil.generate(serviceMock_vm,serviceMock,context);
      System.out.println(serviceMock);
    }
    File serviceImplFile=new File(serviceImpl);
    if (!serviceImplFile.exists()) {
      VelocityContext context=new VelocityContext();
      context.put("package_name",packageName);
      context.put("model",model);
      context.put("mapper",StringUtil.toLowerCaseFirstOne(model));
      context.put("ctime",ctime);
      VelocityUtil.generate(serviceImpl_vm,serviceImpl,context);
      System.out.println(serviceImpl);
    }
  }
  System.out.println("========== ????Service ==========");
}
