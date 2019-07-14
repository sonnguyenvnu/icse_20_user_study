/** 
 * ??????????
 * @param targetMinute
 */
private static void clearCollectionCost(String targetMinute){
  try {
    if (targetMinute == "" || "".equals(targetMinute)) {
      return;
    }
    long targetMinuteLong=NumberUtil.toLong(targetMinute);
    if (targetMinuteLong == 0) {
      return;
    }
    for (    String key : COLLECTION_COST_TIME_MAP_ALL.keySet()) {
      long minute=NumberUtil.toLong(key);
      if (minute < targetMinuteLong) {
        COLLECTION_COST_TIME_MAP_ALL.remove(key);
      }
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
}
