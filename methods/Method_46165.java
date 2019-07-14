/** 
 * ?? Sofa Runtime ?????????????????
 */
private void printUserData(String dataId,UserData userData){
  StringBuilder sb=new StringBuilder();
  int count=0;
  if (userData != null && userData.getZoneData() != null) {
    Map<String,List<String>> oneUserData=userData.getZoneData();
    for (    Map.Entry<String,List<String>> entry : oneUserData.entrySet()) {
      sb.append("  --- ").append(entry.getKey()).append("\n");
      for (      String provider : entry.getValue()) {
        sb.append("   >>> ").append((String)provider).append("\n");
        ++count;
      }
    }
  }
  if (LOGGER.isInfoEnabled()) {
    LOGGER.info(LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_URLS_HANDLE,dataId,count,sb.toString()));
  }
}
