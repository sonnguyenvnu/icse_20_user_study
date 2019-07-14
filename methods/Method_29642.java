/** 
 * ????
 * @param appDesc       ????
 * @param appAudit      ????
 * @param templatePath  ????
 * @param customCharset ??
 */
public synchronized static String createText(VelocityEngine engine,AppDesc appDesc,AppAudit appAudit,AppDailyData appDailyData,List<InstanceAlertValueResult> instanceAlertValueResultList,String templatePath,String customCharset){
  if (!StringUtils.isEmpty(customCharset)) {
    charset=customCharset;
  }
  Properties p=new Properties();
  p.setProperty("file.resource.loader.path",Thread.currentThread().getContextClassLoader().getResource("").getPath());
  p.setProperty(Velocity.ENCODING_DEFAULT,"UTF-8");
  p.setProperty(Velocity.INPUT_ENCODING,"UTF-8");
  p.setProperty(Velocity.OUTPUT_ENCODING,"UTF-8");
  Velocity.init(p);
  logger.info("velocity: init done.");
  VelocityContext context=new VelocityContext();
  context.put("appDesc",appDesc);
  context.put("appAudit",appAudit);
  context.put("appDailyData",appDailyData);
  context.put("instanceAlertValueResultList",instanceAlertValueResultList);
  context.put("numberTool",new NumberTool());
  context.put("ccDomain",ConstUtils.CC_DOMAIN);
  context.put("decimalFormat",new DecimalFormat("###,###"));
  context.put("StringUtils",StringUtils.class);
  FileOutputStream fos=null;
  StringWriter writer=null;
  try {
    Template template=engine.getTemplate(templatePath);
    writer=new StringWriter();
    template.merge(context,writer);
  }
 catch (  ResourceNotFoundException ex) {
    logger.error("error: velocity vm resource not found.",ex);
  }
catch (  ParseErrorException ex) {
    logger.error("error: velocity parse vm file error.",ex);
  }
catch (  MethodInvocationException ex) {
    logger.error("error: velocity template merge.",ex);
  }
catch (  Exception ex) {
    logger.error("error",ex);
  }
 finally {
    try {
      if (writer != null) {
        writer.close();
      }
    }
 catch (    IOException e) {
      logger.error("error: close writer",e);
    }
    try {
      if (fos != null) {
        fos.close();
      }
    }
 catch (    IOException e) {
      logger.error("error: close output stream.",e);
    }
  }
  logger.info("velocity: create text done.");
  if (writer != null) {
    return writer.toString();
  }
  return null;
}
