@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object usedMemoryObject=getValueFromRedisInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.used_memory.getValue());
  long usedMemory=NumberUtils.toLong(usedMemoryObject.toString());
  if (usedMemory < MIN_CHECK_MEMORY) {
    return null;
  }
  Object memFragmentationRatioObject=getValueFromRedisInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.mem_fragmentation_ratio.getValue());
  if (memFragmentationRatioObject == null) {
    return null;
  }
  double memFragmentationRatio=NumberUtils.toDouble(memFragmentationRatioObject.toString());
  boolean compareRight=isCompareDoubleRight(instanceAlertConfig,memFragmentationRatio);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  InstanceAlertValueResult instanceAlertValueResult=new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(memFragmentationRatio),instanceInfo.getAppId(),EMPTY);
  instanceAlertValueResult.setOtherInfo(String.format("?????%s MB",String.valueOf(changeByteToMB(usedMemory))));
  return Arrays.asList();
}
