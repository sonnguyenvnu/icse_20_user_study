private Properties loadNacosData(String dataId,String group,String fileExtension){
  String data=null;
  try {
    data=configService.getConfig(dataId,group,timeout);
    if (!StringUtils.isEmpty(data)) {
      log.info(String.format("Loading nacos data, dataId: '%s', group: '%s'",dataId,group));
      if (fileExtension.equalsIgnoreCase("properties")) {
        Properties properties=new Properties();
        properties.load(new StringReader(data));
        return properties;
      }
 else       if (fileExtension.equalsIgnoreCase("yaml") || fileExtension.equalsIgnoreCase("yml")) {
        YamlPropertiesFactoryBean yamlFactory=new YamlPropertiesFactoryBean();
        yamlFactory.setResources(new ByteArrayResource(data.getBytes()));
        return yamlFactory.getObject();
      }
    }
  }
 catch (  NacosException e) {
    log.error("get data from Nacos error,dataId:{}, ",dataId,e);
  }
catch (  Exception e) {
    log.error("parse data from Nacos error,dataId:{},data:{},",dataId,data,e);
  }
  return EMPTY_PROPERTIES;
}
