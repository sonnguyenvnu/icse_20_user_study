private Properties createProperties(ConfigInfo configInfo){
  Properties properties=new Properties();
  String content=configInfo == null ? null : configInfo.getContent();
  if (StringUtils.hasText(content)) {
    try {
      properties.load(new StringReader(content));
    }
 catch (    IOException e) {
      throw new IllegalStateException("The format of content is a properties");
    }
  }
  return properties;
}
