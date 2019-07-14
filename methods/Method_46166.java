/** 
 * ?? Sofa Runtime ???????????Config Data ??
 */
private void printConfigData(String dataId,ConfigData configData){
  StringBuilder sb=new StringBuilder();
  int count=0;
  if (configData != null && StringUtils.isNotBlank(configData.getData())) {
    final String[] split=StringUtils.split(configData.getData(),CONFIG_SEPARATOR);
    List<String> dataList=Arrays.asList(split);
    for (    String provider : dataList) {
      sb.append("  >>> ").append(provider).append("\n");
      count++;
    }
  }
  if (LOGGER.isInfoEnabled()) {
    LOGGER.info(LogCodes.getLiteLog("Receive RPC config info: service[{0}]\n  usable config info[{1}]\n{2}",dataId,count,sb.toString()));
  }
}
