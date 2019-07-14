/** 
 * ??json??????JavaBean
 * @param json
 * @return
 */
private ClientReportBean checkReportJson(String json){
  if (StringUtils.isNotBlank(json)) {
    try {
      return JsonUtil.fromJson(json,ClientReportBean.class);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
  return null;
}
