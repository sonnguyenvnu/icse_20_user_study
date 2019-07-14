/** 
 * ??
 * @param ccReportBean
 */
public static void reportData(ClientReportBean ccReportBean){
  if (ccReportBean == null) {
    logger.error("ccReportBean is null!");
  }
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put(ClientReportConstant.JSON_PARAM,JsonUtil.toJson(ccReportBean));
  parameters.put(ClientReportConstant.CLIENT_VERSION,ConstUtils.CLIENT_VERSION);
  try {
    HttpUtils.doPost(ConstUtils.CACHECLOUD_REPORT_URL,parameters);
  }
 catch (  Exception e) {
    logger.error("cachecloud reportData exception: " + e.getMessage());
    UsefulDataCollector.collectException(e,"",System.currentTimeMillis(),ClientExceptionType.CLIENT_EXCEPTION_TYPE);
  }
}
