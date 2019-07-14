private Properties loadDiamondData(String dataId,String diamondGroup){
  try {
    String data=ConfigService.getConfig(dataId,diamondGroup,3000L);
    if (StringUtils.isEmpty(data)) {
      return null;
    }
    if (dataId.endsWith(".properties")) {
      Properties properties=new Properties();
      log.info(String.format("Loading acm data, dataId: '%s', group: '%s'",dataId,diamondGroup));
      properties.load(new StringReader(data));
      return properties;
    }
 else     if (dataId.endsWith(".yaml") || dataId.endsWith(".yml")) {
      YamlPropertiesFactoryBean yamlFactory=new YamlPropertiesFactoryBean();
      yamlFactory.setResources(new ByteArrayResource(data.getBytes()));
      return yamlFactory.getObject();
    }
  }
 catch (  Exception e) {
    if (e instanceof ConfigException) {
      log.error("DIAMOND-100500:" + dataId + ", " + e.toString(),e);
    }
 else {
      log.error("DIAMOND-100500:" + dataId,e);
    }
  }
  return null;
}
