static List<String> loadFile(String path){
  List<String> blackPrefixList=new ArrayList<String>();
  InputStream input=null;
  try {
    input=BlackListFileLoader.class.getResourceAsStream(path);
    if (input != null) {
      readToList(input,"UTF-8",blackPrefixList);
    }
    String overStr=SofaConfigs.getStringValue(SofaOptions.CONFIG_SERIALIZE_BLACKLIST_OVERRIDE,"");
    if (StringUtils.isNotBlank(overStr)) {
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("Serialize blacklist will override with configuration: {}",overStr);
      }
      overrideBlackList(blackPrefixList,overStr);
    }
  }
 catch (  Exception e) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(e.getMessage(),e);
    }
  }
 finally {
    closeQuietly(input);
  }
  return blackPrefixList;
}
