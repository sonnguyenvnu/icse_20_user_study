/** 
 * ??targetMinute??????
 * @param targetMinute
 */
private static void clearValueLength(String targetMinute){
  try {
    if (targetMinute == "" || "".equals(targetMinute)) {
      return;
    }
    long targetMinuteLong=NumberUtil.toLong(targetMinute);
    if (targetMinuteLong == 0) {
      return;
    }
    for (    String key : DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.keySet()) {
      long minute=NumberUtil.toLong(key);
      if (minute < targetMinuteLong) {
        DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.remove(key);
      }
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
}
