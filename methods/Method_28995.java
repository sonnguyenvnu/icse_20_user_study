/** 
 * ?????
 * @param lastMinute
 */
private List<Map<String,Object>> collectReportValueDistriData(String lastMinute){
  try {
    Map<ValueLengthModel,Long> jedisValueLengthMap=UsefulDataCollector.getValueLengthLastMinute(lastMinute);
    if (jedisValueLengthMap == null || jedisValueLengthMap.isEmpty()) {
      return Collections.emptyList();
    }
    List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    for (    Entry<ValueLengthModel,Long> entry : jedisValueLengthMap.entrySet()) {
      ValueLengthModel model=entry.getKey();
      Long count=entry.getValue();
      Map<String,Object> tempMap=new HashMap<String,Object>();
      tempMap.put(ClientReportConstant.VALUE_DISTRI,model.getRedisValueSizeEnum().getValue());
      tempMap.put(ClientReportConstant.VALUE_COUNT,count);
      tempMap.put(ClientReportConstant.VALUE_COMMAND,model.getCommand());
      tempMap.put(ClientReportConstant.VALUE_HOST_PORT,model.getHostPort());
      tempMap.put(ClientReportConstant.CLIENT_DATA_TYPE,ClientCollectDataTypeEnum.VALUE_LENGTH_DISTRI_TYPE.getValue());
      list.add(tempMap);
    }
    return list;
  }
 catch (  Exception e) {
    UsefulDataCollector.collectException(e,"",System.currentTimeMillis(),ClientExceptionType.CLIENT_EXCEPTION_TYPE);
    logger.error("collectReportValueDistriData:" + e.getMessage(),e);
    return Collections.emptyList();
  }
}
